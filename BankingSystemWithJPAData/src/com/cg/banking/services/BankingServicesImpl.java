package com.cg.banking.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cg.banking.beans.Account;
import com.cg.banking.beans.Address;
import com.cg.banking.beans.Customer;
import com.cg.banking.beans.Transaction;
import com.cg.banking.daoservices.BankingDAOServices;
import com.cg.banking.daoservices.BankingDAOServicesAccount;
import com.cg.banking.daoservices.BankingDAOServicesTrans;
import com.cg.banking.exceptions.AccountBlockedException;
import com.cg.banking.exceptions.AccountNotFoundException;
import com.cg.banking.exceptions.BankingServicesDownException;
import com.cg.banking.exceptions.CustomerNotFoundException;
import com.cg.banking.exceptions.InsufficientAmountException;
import com.cg.banking.exceptions.InvalidAccountTypeException;
import com.cg.banking.exceptions.InvalidAmountException;
import com.cg.banking.exceptions.InvalidPinNumberException;
import com.cg.banking.utilities.BankingUtility;

@Component("bankingServices")
public class BankingServicesImpl implements BankingServices {
	
	@Autowired
	private BankingDAOServices daoServices;
	@Autowired
	private BankingDAOServicesAccount daoServicesAccount;
	@Autowired
	private BankingDAOServicesTrans daoServicesTrans;
	Account account;
	Customer customer;
	Transaction transaction;
	@Override
	public int acceptCustomerDetails(String firstName, String lastName, String customerEmailId, String panCard,
			String localAddressCity, String localAddressState, int localAddressPinCode) throws BankingServicesDownException {
		customer=daoServices.save(new Customer(firstName, lastName, customerEmailId, panCard, new Address(localAddressPinCode, localAddressCity, localAddressState)));
		return customer.getCustomerId();
	}

	@Override
	public long openAccount(int customerId, String accountType, float initBalance) throws InvalidAmountException,
	CustomerNotFoundException, InvalidAccountTypeException, BankingServicesDownException {
		if(daoServices.findOne(customerId)==null)
			throw new CustomerNotFoundException("CustomerID : "+customerId+" not found.");
		if(initBalance<=0)
			throw new InvalidAmountException("Initial balance should not be less than 1");
		if(!accountType.equals("Savings")&&!accountType.equals("savings")&&!accountType.equals("SAVINGS")&&
				!accountType.equals("Current")&&!accountType.equals("current")&&!accountType.equals("CURRENT")&&
				!accountType.equals("Salary")&&!accountType.equals("salary")&&!accountType.equals("SALARY"))
			throw new InvalidAccountTypeException("Account Type : "+accountType+" is not valid.");
			customer=daoServices.findOne(customerId);
			account=new Account(accountType, initBalance);
			account.setCustomer(customer);
			account.setAccountType(accountType);
			account.setAccountBalance(initBalance);
			account.setStatus(BankingUtility.ACCOUNT_STATUS_ACTIVE);
			daoServicesAccount.save(account);
		return account.getAccountNo();
	}

	@Override
	public float depositAmount(int customerId, long accountNo, float amount) throws CustomerNotFoundException,
	AccountNotFoundException, BankingServicesDownException, AccountBlockedException {
		
		if(getCustomerDetails(customerId)==null)
			throw new CustomerNotFoundException("CustomerID : "+customerId+" not found.");
		if(getAccountDetails(customerId, accountNo)==null)
			throw new AccountNotFoundException("AccountNo : "+accountNo+" not found.");
		if(!getAccountDetails(customerId, accountNo).getStatus().equals(BankingUtility.ACCOUNT_STATUS_ACTIVE))
			throw new AccountBlockedException("AccountNo "+accountNo+" is Blocked");
		account=getAccountDetails(customerId, accountNo);
		account.setAccountBalance(account.getAccountBalance()+amount);
		daoServicesAccount.saveAndFlush(account);
		transaction=new Transaction(amount, "DEPOSIT");
		transaction.setAccount(account);
		daoServicesTrans.save(transaction);
		return this.getAccountDetails(customerId, accountNo).getAccountBalance();
	}

	@Override
	public float withdrawAmount(int customerId, long accountNo, float amount, int pinNumber)
			throws InsufficientAmountException, CustomerNotFoundException, AccountNotFoundException,
			InvalidPinNumberException, BankingServicesDownException, AccountBlockedException {
		Account account=getAccountDetails(customerId, accountNo);
		if(getCustomerDetails(customerId)==null)
			throw new CustomerNotFoundException("CustomerID : "+customerId+" not found.");
		if(getAccountDetails(customerId, accountNo)==null)
			throw new AccountNotFoundException("AccountNo : "+accountNo+" not found.");
		if(!getAccountDetails(customerId, accountNo).getStatus().equals(BankingUtility.ACCOUNT_STATUS_ACTIVE))
			throw new AccountBlockedException("AccountNo "+accountNo+" is Blocked");
		if(getAccountDetails(customerId, accountNo).getPinNumber()!=pinNumber){
			getAccountDetails(customerId, accountNo).setPinCounter(getAccountDetails(customerId, accountNo).getPinCounter()+1);
			if(getAccountDetails(customerId, accountNo).getPinCounter()>2)
				getAccountDetails(customerId, accountNo).setStatus(BankingUtility.ACCOUNT_STATUS_BLOCKED);
			throw new InvalidPinNumberException("Invalid PIN Number.");
		}
		if(amount>getAccountDetails(customerId, accountNo).getAccountBalance())
			throw new InsufficientAmountException("Insufficient Amount");
		while(getAccountDetails(customerId, accountNo)!=null&&getAccountDetails(customerId, accountNo).getPinCounter()<3)
			if(pinNumber==getAccountDetails(customerId, accountNo).getPinNumber())
				if(amount<=getAccountDetails(customerId, accountNo).getAccountBalance()) {
					account=getAccountDetails(customerId, accountNo);
					account.setAccountBalance(account.getAccountBalance()-amount);
					daoServicesAccount.saveAndFlush(account);
					transaction=new Transaction(amount, "WITHDRAWAL");
					transaction.setAccount(account);
					daoServicesTrans.save(transaction);
					return this.getAccountDetails(customerId, accountNo).getAccountBalance();
				}
		return 0;
	}

	@Override
	public boolean fundTransfer(int customerIdTo, long accountNoTo, int customerIdFrom, long accountNoFrom,
			float transferAmount, int pinNumber) throws InsufficientAmountException, CustomerNotFoundException,
	AccountNotFoundException, InvalidPinNumberException, BankingServicesDownException, AccountBlockedException {
				Account accountFrom=getAccountDetails(customerIdFrom, accountNoFrom);
				Account accountTo=getAccountDetails(customerIdTo, accountNoTo);
				if(getCustomerDetails(customerIdTo)==null)
					throw new CustomerNotFoundException("CustomerID : "+customerIdTo+" not found.");
				if(getAccountDetails(customerIdTo, accountNoTo)==null)
					throw new AccountNotFoundException("AccountNo : "+accountNoTo+" not found.");
				if(!getAccountDetails(customerIdFrom, accountNoFrom).getStatus().equals(BankingUtility.ACCOUNT_STATUS_ACTIVE))
					throw new AccountBlockedException("AccountNo "+accountNoTo+" is Blocked");
				if(getCustomerDetails(customerIdFrom)==null)
					throw new CustomerNotFoundException("CustomerID : "+customerIdFrom+" not found.");
				if(getAccountDetails(customerIdFrom, accountNoFrom)==null)
					throw new AccountNotFoundException("AccountNo : "+accountNoFrom+" not found.");
				if(!getAccountDetails(customerIdTo, accountNoTo).getStatus().equals(BankingUtility.ACCOUNT_STATUS_ACTIVE))
					throw new AccountBlockedException("AccountNo "+accountNoFrom+" is Blocked");
				if(transferAmount>getAccountDetails(customerIdFrom, accountNoFrom).getAccountBalance())
					throw new InsufficientAmountException("Insufficient Amount");
				if(getAccountDetails(customerIdFrom, accountNoFrom).getPinNumber()!=pinNumber){
					getAccountDetails(customerIdFrom, accountNoFrom).setPinCounter(getAccountDetails(customerIdFrom, accountNoFrom).getPinCounter()+1);
					if(getAccountDetails(customerIdFrom, accountNoFrom).getPinCounter()>2)
						getAccountDetails(customerIdFrom, accountNoFrom).setStatus(BankingUtility.ACCOUNT_STATUS_BLOCKED);
					throw new InvalidPinNumberException("Invalid PIN Number.");
				}
				if(pinNumber==getAccountDetails(customerIdFrom, accountNoFrom).getPinNumber()) {
					if(transferAmount<=getAccountDetails(customerIdFrom, accountNoFrom).getAccountBalance()) {
				accountTo=getAccountDetails(customerIdTo, accountNoTo);
				accountTo=getAccountDetails(customerIdTo, accountNoTo);
				accountTo.setAccountBalance(accountTo.getAccountBalance()+transferAmount);
				accountFrom.setAccountBalance(accountFrom.getAccountBalance()-transferAmount);
				daoServicesAccount.saveAndFlush(accountTo);
				daoServicesAccount.saveAndFlush(accountFrom);
				Transaction transactionTo=new Transaction(transferAmount, "DEPOSIT");
				Transaction transactionFrom=new Transaction(transferAmount, "WITHDRAWAL");
				transactionTo.setAccount(accountTo);
				transactionFrom.setAccount(accountFrom);
				daoServicesTrans.save(transactionTo);
				daoServicesTrans.save(transactionFrom);
				return true;
			}
		}
		return false;
	}

	@Override
	public Customer getCustomerDetails(int customerId) throws CustomerNotFoundException, BankingServicesDownException {
		if(daoServices.findOne(customerId)==null)
			throw new CustomerNotFoundException("CustomerID : "+customerId+" not found.");
		return daoServices.findOne(customerId);
	}

	@Override
	public Account getAccountDetails(int customerId, long accountNo)
			throws CustomerNotFoundException, AccountNotFoundException, BankingServicesDownException {
		if(daoServices.findOne(customerId)==null)
			throw new CustomerNotFoundException("CustomerID : "+customerId+" not found.");
		if(daoServicesAccount.findOne(accountNo)==null)
			throw new AccountNotFoundException("AccountNo : "+accountNo+" not found.");
		return daoServicesAccount.findOne(accountNo);
	}

	@Override
	public int generateNewPin(int customerId, long accountNo)
			throws CustomerNotFoundException, AccountNotFoundException, BankingServicesDownException {
		if(getCustomerDetails(customerId)==null)
			throw new CustomerNotFoundException("CustomerID : "+customerId+" not found.");
		if(getAccountDetails(customerId, accountNo)==null)
			throw new AccountNotFoundException("AccountNo : "+accountNo+" not found.");
		account=getAccountDetails(customerId, accountNo);
		account.setPinNumber(1111);
		daoServicesAccount.saveAndFlush(account);
		return account.getPinNumber();
	}

	@Override
	public boolean changeAccountPin(int customerId, long accountNo, int oldPinNumber, int newPinNumber)
			throws CustomerNotFoundException, AccountNotFoundException, InvalidPinNumberException,
			BankingServicesDownException {
		if(getCustomerDetails(customerId)==null)
			throw new CustomerNotFoundException("CustomerID : "+customerId+" not found.");
		if(getAccountDetails(customerId, accountNo)==null)
			throw new AccountNotFoundException("AccountNo : "+accountNo+" not found.");
		if(getAccountDetails(customerId, accountNo).getPinNumber()!=oldPinNumber){
			getAccountDetails(customerId, accountNo).setPinCounter(getAccountDetails(customerId, accountNo).getPinCounter()+1);
			if(getAccountDetails(customerId, accountNo).getPinCounter()>2)
				getAccountDetails(customerId, accountNo).setStatus(BankingUtility.ACCOUNT_STATUS_BLOCKED);
			throw new InvalidPinNumberException("Invalid PIN Number.");
		}
		if(oldPinNumber==getAccountDetails(customerId, accountNo).getPinNumber()) {
			oldPinNumber=newPinNumber;
			Account account=getAccountDetails(customerId, accountNo);
			account.setPinNumber(oldPinNumber);
			daoServicesAccount.saveAndFlush(account);
			return true;
		}
		return false;
	}

	@Override
	public List<Customer> getAllCustomerDetails() throws BankingServicesDownException {
		return daoServices.findAll();
	}

	@Override
	public List<Account> getcustomerAllAccountDetails(int customerId)
			throws BankingServicesDownException, CustomerNotFoundException {
		/*if(getCustomerDetails(customerId)==null)
			throw new CustomerNotFoundException("CustomerID : "+customerId+" not found.");
		return daoServices.getAccounts(customerId);*/
		return null;
	}

	@Override
	public List<Transaction> getAccountAllTransaction(int customerId, long accountNo)
			throws BankingServicesDownException, CustomerNotFoundException, AccountNotFoundException {
		/*if(getCustomerDetails(customerId)==null)
			throw new CustomerNotFoundException("CustomerID : "+customerId+" not found.");
		if(getAccountDetails(customerId, accountNo)==null)
			throw new AccountNotFoundException("AccountNo : "+accountNo+" not found.");
		return daoServices.getTransactions(customerId, accountNo);*/
		return null;
	}

	@Override
	public String accountStatus(int customerId, long accountNo) throws BankingServicesDownException,
	CustomerNotFoundException, AccountNotFoundException, AccountBlockedException {
		/*if(getCustomerDetails(customerId)==null)
			throw new CustomerNotFoundException("CustomerID : "+customerId+" not found.");
		if(getAccountDetails(customerId, accountNo)==null)
			throw new AccountNotFoundException("AccountNo : "+accountNo+" not found.");
		if(getAccountDetails(customerId, accountNo).getStatus()!=BankingUtility.ACCOUNT_STATUS_ACTIVE)
			throw new AccountBlockedException("AccountNo "+accountNo+" is Blocked");*/
		return getAccountDetails(customerId, accountNo).getStatus();
	}

	@Override
	public boolean closeAccount(int customerId, long accountNo)
			throws BankingServicesDownException, CustomerNotFoundException, AccountNotFoundException {
		/*if(getCustomerDetails(customerId)==null)
			throw new CustomerNotFoundException("CustomerID : "+customerId+" not found.");
		if(getAccountDetails(customerId, accountNo)==null)
			throw new AccountNotFoundException("AccountNo : "+accountNo+" not found.");
			daoServices.deleteAccount(customerId, accountNo);*/
			return true;
	}

	@Override
	public float showBalance(int customerId, long accountNo, int pinNumber) throws CustomerNotFoundException,
	AccountNotFoundException, BankingServicesDownException, AccountBlockedException {
		/*if(getCustomerDetails(customerId)==null)
			throw new CustomerNotFoundException("CustomerID : "+customerId+" not found.");
		if(getAccountDetails(customerId, accountNo)==null)
			throw new AccountNotFoundException("AccountNo : "+accountNo+" not found.");
		if(getAccountDetails(customerId, accountNo).getStatus()!=BankingUtility.ACCOUNT_STATUS_ACTIVE)
			throw new AccountBlockedException("AccountNo "+accountNo+" is Blocked");
		if(customerId==daoServices.getCustomer(customerId).getCustomerId()
				&&accountNo==daoServices.getAccount(customerId, accountNo).getAccountNo()
				&&pinNumber==daoServices.getAccount(customerId, accountNo).getPinNumber())
			return daoServices.getAccount(customerId, accountNo).getAccountBalance();*/
		return 0;
	}}
