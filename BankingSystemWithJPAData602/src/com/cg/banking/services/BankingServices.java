package com.cg.banking.services;

import java.util.List;

import com.cg.banking.beans.Account;
import com.cg.banking.beans.Customer;
import com.cg.banking.beans.Transaction;
import com.cg.banking.exceptions.AccountBlockedException;
import com.cg.banking.exceptions.AccountNotFoundException;
import com.cg.banking.exceptions.BankingServicesDownException;
import com.cg.banking.exceptions.CustomerNotFoundException;
import com.cg.banking.exceptions.InsufficientAmountException;
import com.cg.banking.exceptions.InvalidAccountTypeException;
import com.cg.banking.exceptions.InvalidAmountException;
import com.cg.banking.exceptions.InvalidPinNumberException;

public interface BankingServices {

	int acceptCustomerDetails(String firstName,String lastName,String customerEmailId,String panCard,String localAddressCity,
			String localAddressState,int localAddressPinCode)throws BankingServicesDownException;



	long openAccount(int customerId,String accountType,float initBalance)
			throws InvalidAmountException,
			CustomerNotFoundException,
			InvalidAccountTypeException,
			BankingServicesDownException;


	float depositAmount(int customerId,long accountNo,float amount)
			throws CustomerNotFoundException,
			AccountNotFoundException,BankingServicesDownException, AccountBlockedException;

	float showBalance(int customerId,long accountNo,int pinNumber)
			throws CustomerNotFoundException,
			AccountNotFoundException,BankingServicesDownException, AccountBlockedException;

	
	float withdrawAmount(int customerId,long accountNo,float amount,int pinNumber)
			throws InsufficientAmountException,CustomerNotFoundException,
			AccountNotFoundException,InvalidPinNumberException,
			BankingServicesDownException ,AccountBlockedException;

	boolean fundTransfer(int customerIdTo,long accountNoTo,int customerIdFrom,long accountNoFrom,float transferAmount,int pinNumber)
			throws InsufficientAmountException,
			CustomerNotFoundException,
			AccountNotFoundException,InvalidPinNumberException,
			BankingServicesDownException, AccountBlockedException  ;



	Customer getCustomerDetails(int customerId)
			throws CustomerNotFoundException,BankingServicesDownException;

	Account getAccountDetails(int customerId,long accountNo)
			throws 
			CustomerNotFoundException,AccountNotFoundException,BankingServicesDownException;

	public int generateNewPin(int customerId,long accountNo)
			throws
			CustomerNotFoundException,AccountNotFoundException ,
			BankingServicesDownException;


	public boolean changeAccountPin(int customerId,long accountNo,int oldPinNumber,int newPinNumber)

			throws CustomerNotFoundException,
			AccountNotFoundException,
			InvalidPinNumberException,BankingServicesDownException  ;

	List<Customer> getAllCustomerDetails()throws BankingServicesDownException;

	List<Account> getcustomerAllAccountDetails(int customerId)
			throws BankingServicesDownException,CustomerNotFoundException;

	List<Transaction> getAccountAllTransaction(int customerId,long accountNo)
			throws BankingServicesDownException,
			CustomerNotFoundException,
			AccountNotFoundException;

	public String accountStatus(int customerId,long accountNo)
			throws BankingServicesDownException,
			CustomerNotFoundException,
			AccountNotFoundException, AccountBlockedException;


	boolean closeAccount(int customerId, long accountNo)
			throws BankingServicesDownException,
			CustomerNotFoundException,
			AccountNotFoundException;
}