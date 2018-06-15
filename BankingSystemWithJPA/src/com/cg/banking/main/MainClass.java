package com.cg.banking.main;

import java.util.List;
import java.util.Scanner;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.cg.banking.beans.*;
import com.cg.banking.daoservices.BankingDAOServices;
import com.cg.banking.daoservices.BankingDOAOServicesImpl;
import com.cg.banking.services.*;
import com.cg.banking.exceptions.AccountBlockedException;
import com.cg.banking.exceptions.AccountNotFoundException;
import com.cg.banking.exceptions.BankingServicesDownException;
import com.cg.banking.exceptions.CustomerNotFoundException;
import com.cg.banking.exceptions.InsufficientAmountException;
import com.cg.banking.exceptions.InvalidAccountTypeException;
import com.cg.banking.exceptions.InvalidAmountException;
import com.cg.banking.exceptions.InvalidPinNumberException;

public class MainClass {
	public static void main(String[] args) throws BankingServicesDownException, InvalidAmountException, CustomerNotFoundException, InvalidAccountTypeException, AccountNotFoundException, AccountBlockedException, InsufficientAmountException, InvalidPinNumberException {
//		BankingDAOServices dao=new BankingDOAOServicesImpl();
		ApplicationContext applicationContext=new ClassPathXmlApplicationContext("projectbeans.xml");
		BankingServices bankingServices=(BankingServices) applicationContext.getBean("bankingServices");
		bankingServices.acceptCustomerDetails("Cross", "Fire", "crossh@abcd.com", "s1", "pune", "maharastra", 411062);
		bankingServices.acceptCustomerDetails("Crfsoss", "Fire", "crossh@abcd.com", "s1", "pune", "maharastra", 411062);
		bankingServices.openAccount(2, "SAVINGS", 20000);
		bankingServices.openAccount(1, "CURRENT", 60000);
//		bankingServices.getAccountDetails(2, 1);
		bankingServices.depositAmount(2, 1, 30000);
		bankingServices.generateNewPin(2, 1);
		bankingServices.withdrawAmount(2, 1, 25000, 1111);
		bankingServices.fundTransfer(1, 2, 2, 1, 3000, 1111);
//		System.out.println(bankingServices.getAllCustomerDetails());
//		System.out.println(bankingServices.getcustomerAllAccountDetails(2));
		System.out.println(bankingServices.getAccountAllTransaction(2, 1));
//		int id1=bankingServices.acceptCustomerDetails("Cross", "Fire", "crossh@abcd.com", "s1", "pune", "maharastra", 411062, "mumbai", "maharastra", 411052);
//		System.out.println(id1);
		
	}
}
		










/*BankingServices bankingServies = new BankingServicesImpl();
		int choice=0;
		do {
			Scanner scan = new Scanner(System.in);
			try {
				System.out.println("MENU:");
				System.out.println("\t 1.  Registration of Customer");
				System.out.println("\t 2.  Opening an Account ");
				System.out.println("\t 3.  Genearte PIN For The Account ");
				System.out.println("\t 4.  Deposit the Amount ");
				System.out.println("\t 5.  Withdraw the Amount ");
				System.out.println("\t 6.  Fund Transfer ");
				System.out.println("\t 7.  Balance Enquiry: ");
				System.out.println("\t 8.  Changing PIN For The Account");
				System.out.println("\t 9.  Account Status ");
				System.out.println("\t 10. Get All Transaction of An Account ");
				System.out.println("\t 11. Get Acccount Details of a Customer");
				System.out.println("\t 12. Get All Acccount Details of a Customer");
				System.out.println("\t 13. Customer Details ");
				System.out.println("\t 14. All Customer Details ");
				System.out.println("\t 15. Close The Account ");
				System.out.println("\t 16. Exit ");
				System.out.print("Enter your choice: ");
				choice=scan.nextInt();
				switch(choice) {
				case 1:
					System.out.print("\t Enter FirstName: ");
					String  firstName = scan.next();
					System.out.print("\t Enter LastName: ");
					String lastName = scan.next();
					System.out.print("\t Enter Email-ID:  ");
					String emailId = scan.next();
					System.out.print("\t Enter PAN Number: ");
					String panCard = scan.next();
					System.out.println("\t Enter Local Address:");
					System.out.print("\t Enter City : ");
					String  localAddressCity = scan.next();
					System.out.print("\t Enter State : ");
					String localAddressState = scan.next();
					System.out.print("\t Enter PINCODE: ");
					int localAddressPinCode = scan.nextInt();
					System.out.println("\t Enter Permanent Address:");
					System.out.print("\t Enter  City : ");
					String  homeAddressCity = scan.next();
					System.out.print("\t Enter  State : ");
					String homeAddressState = scan.next();
					System.out.print("\t Enter PINCODE: ");
					int homeAddressPinCode = scan.nextInt();
					int gencustomerid = bankingServies.acceptCustomerDetails(firstName, lastName, emailId, panCard, localAddressCity,localAddressState, localAddressPinCode, homeAddressCity, homeAddressState, homeAddressPinCode);
					System.out.println("Your CustomerId is : "+gencustomerid);
					break;
				case 2:
					System.out.print("\t Enter CustomerID : ");
					int customerId = scan.nextInt();
					System.out.print("\t Enter Type of Account need to be Created : ");
					String accountType=scan.next();
					System.out.print("\t Enter Initial Balance : ");
					float initBalance=scan.nextFloat();
					long genaccountnumber=bankingServies.openAccount(customerId,accountType,initBalance);
					System.out.println("\t Generated Account Number : "+genaccountnumber+" for customerID : "+customerId);
					break;
				case 3:
					System.out.print("\t Enter CustomerID : ");
					int customerIdcase3 = scan.nextInt();
					System.out.print("\t Enter Account No:");
					long accountNoCase3= scan.nextLong();
					int genpin=bankingServies.generateNewPin(customerIdcase3,accountNoCase3);
					System.out.println(" PIN generated :"+genpin+", for AccountNo: "+ accountNoCase3+",for CustomerId: "+customerIdcase3);
					break;
				case 4:
					System.out.print("\t Enter CustomerID : ");
					int customerIdcase4 = scan.nextInt();
					System.out.print("\t Enter Account No:");
					long accountNoCase4= scan.nextLong();
					System.out.print("Amount to be Deposited : ");
					float depositAmountCase4=scan.nextFloat();
					float totalBalancead1=bankingServies.depositAmount(customerIdcase4, accountNoCase4, depositAmountCase4);
					System.out.println("Amount Deposited is : "+depositAmountCase4+",for AccountNo :"+accountNoCase4+",for CustomerID : "+customerIdcase4);
					System.out.println(totalBalancead1);
					break;
				case 5:
					System.out.print("\t Enter CustomerID : ");
					int customerIdcase5 = scan.nextInt();
					System.out.print("\t Enter Account No:");
					long accountNoCase5= scan.nextLong();
					System.out.print("Amount to be Withdrawn : ");
					float withdrawAmountCase5=scan.nextFloat();
					System.out.print("Enter PIN Number: ");
					int pinCase5=scan.nextInt();
					float totalBalanceaw1=bankingServies.withdrawAmount(customerIdcase5, accountNoCase5, withdrawAmountCase5, pinCase5);
					System.out.println("Amount Withdrawn is : "+withdrawAmountCase5+",for AccountNo :"+accountNoCase5+",for CustomerID : "+customerIdcase5);
					System.out.println(totalBalanceaw1);
					break;
				case 6:
					System.out.print("\t Enter Giver CustomerID :  ");
					int fromCustomerIdCase6 = scan.nextInt();
					System.out.print("\t Enter  Giver Account No:");
					long fromAccountNoCase6= scan.nextLong();
					System.out.print("\t Enter Receiver CustomerID :  ");
					int toCustomerIdCase6 = scan.nextInt();
					System.out.print("\t Enter Receiver Account No:");
					long toAccountNoCase6= scan.nextLong();
					System.out.print("\t Enter amount to be Transferred : ");
					float amountCase6=scan.nextFloat();
					System.out.print("Enter Giver Account PIN Number : ");
					int pinCase6 = scan.nextInt();
					boolean b=bankingServies.fundTransfer(toCustomerIdCase6, toAccountNoCase6, fromCustomerIdCase6, fromAccountNoCase6, amountCase6, pinCase6);
					System.out.println(" Transferred Amount : "+amountCase6+
							"\n From Account No : "+fromAccountNoCase6+
							"\n of CustomerID :  "+fromCustomerIdCase6+
							"\n To Account No : "+toAccountNoCase6+
							"\n of CustomerID :  "+toAccountNoCase6+
							b);
					break;
				case 7:
					System.out.print("\t Enter CustomerID :  ");
					int customerIdCase7 = scan.nextInt();
					System.out.print("\t Enter Account No :");
					long accountNoCase7= scan.nextLong();
					System.out.print("Enter PIN : ");
					int pinCase7=scan.nextInt();
					float amountCase7 = bankingServies.showBalance(customerIdCase7, accountNoCase7, pinCase7);
					System.out.println("Account Balance : "+amountCase7);
					break;
				case 8:
					System.out.print("\t Enter CustomerID :  ");
					int customerIdCase8 = scan.nextInt();
					System.out.print("\t Enter Account No :");
					long accountNoCase8= scan.nextLong();
					System.out.print("Enter Old PIN : ");
					int oldPinCase8=scan.nextInt();
					System.out.print("Enter New PIN : ");
					int newPinCase8=scan.nextInt();
					boolean aCase8=bankingServies.changeAccountPin(customerIdCase8, accountNoCase8, oldPinCase8, newPinCase8);
					System.out.println("New PIN Allocated: "+newPinCase8+",for Account Number : "+accountNoCase8+",of CustomerID : "+customerIdCase8);
					break;
				case 9:
					System.out.print("\t Enter CustomerID :  ");
					int customerIdCase9 = scan.nextInt();
					System.out.print("\t Enter Account No :");
					long accountNoCase9= scan.nextLong();
					String aCase9= bankingServies.accountStatus(customerIdCase9, accountNoCase9);
					System.out.println("Account Status : "+aCase9);
					break;
				case 10:
					System.out.print("\t Enter CustomerID :  ");
					int customerIdCase10 = scan.nextInt();
					System.out.print("\t Enter Account No :");
					long accountNoCase10= scan.nextLong();
					List<Transaction> transaction = bankingServies.getAccountAllTransaction(customerIdCase10,accountNoCase10);
					System.out.println("Transaction List : "+transaction);
					break;
				case 11:
					System.out.print("\t Enter CustomerID :  ");
					int customerIdCase11 = scan.nextInt();
					System.out.print("\t Enter Account No :");
					long accountNoCase11= scan.nextLong();
					Account accountCase11=bankingServies.getAccountDetails(customerIdCase11, accountNoCase11);
					System.out.println("Account Details of AccountNo: "+accountNoCase11+",of CustomerID : "+customerIdCase11+
							"\n"+accountCase11);
					break;
				case 12:
					System.out.print("\t Enter CustomerID :  ");
					int customerIdCase12 = scan.nextInt();
					List<Account> accountCase12=bankingServies.getcustomerAllAccountDetails(customerIdCase12 );
					System.out.println("All Account Details of CustomerID : "+customerIdCase12+"\n"+accountCase12);
					break;
				case 13:
					System.out.print("\t Enter CustomerID :  ");
					int customerIdCase13 = scan.nextInt();
					Customer customerCase13=bankingServies.getCustomerDetails( customerIdCase13);
					System.out.println("Customer Details of CustomerID : "+customerIdCase13+"\n"+customerCase13);
					break;
				case 14:
					List<Customer> customerCase14=bankingServies.getAllCustomerDetails();
					System.out.println("All Customer Details : "+customerCase14);
					break;
				case 15:
					System.out.print("\t Enter CustomerID :  ");
					int customerIdCase15 = scan.nextInt();
					System.out.print("\t Enter Account No :");
					long accountNoCase15= scan.nextLong();
					boolean aCase15=bankingServies.closeAccount(customerIdCase15, accountNoCase15);
					System.out.println("Account Closesd Status : "+aCase15);
					break;
				case 16:
					System.out.println("You are go to Exit,Thank You!!!");
					break;
				default:
					System.out.println("Please Enter Valid Option!!!");
					break;
				}
			}catch (AccountBlockedException e) {
				e.printStackTrace();
			}catch (AccountNotFoundException e) {
				e.printStackTrace();
			}catch (BankingServicesDownException e) {
				e.printStackTrace();
			}catch (CustomerNotFoundException e) {
				e.printStackTrace();
			}catch (InsufficientAmountException e) {
				e.printStackTrace();
			}catch (InvalidAccountTypeException e) {
				e.printStackTrace();
			}catch (InvalidAmountException e) {
				e.printStackTrace();
			}catch (InvalidPinNumberException e) {
				e.printStackTrace();
			}

		}while(choice != 16);
	}
}*/

/*BankingDAOServices bankingdaoservices=new BankingDAOServicesImpl();

			int customer1 = bankingServies.acceptCustomerDetails("satish", "mahajan", "satish@abcd.com", "s1", "pune", "maharastra", 411062, "mumbai", "maharastra", 411052);
			//System.out.println(customer1);

			long account11=bankingServies.openAccount(customer1, "Savings", 20000);
			//System.out.println(account11);

			long account12=bankingServies.openAccount(customer1, "Salary", 30000);
			//System.out.println(account12);

			long account13=bankingServies.openAccount(customer1, "Current", 50000);
			System.out.println(account13);

			long account14=bankingServies.openAccount(customer1, "Transaction", 50000);
			System.out.println(account14);

			long account15=bankingServies.openAccount(customer1, "Child", 50000);
			System.out.println(account15);

			Account[] account=bankingdaoservices.getAccounts(customer1);
			System.out.println(account.length);

			float totalBalancead1=bankingServies.depositAmount(customer1, account11, 10000);
			System.out.println(totalBalancead1);

			float totalBalancead2=bankingServies.depositAmount(customer1, account12, 10000);
			System.out.println(totalBalancead2);

			int pin1=bankingServies.generateNewPin(customer1, account11);
			System.out.println(pin1);

			int pin2=bankingServies.generateNewPin(customer1, account12);
			System.out.println(pin2);

			int pin3=bankingServies.generateNewPin(customer1, account13);
			System.out.println(pin3);


			float totalBalanceaw1=bankingServies.withdrawAmount(customer1, account11, 100, pin1);
			System.out.println(totalBalanceaw1);
			float totalBalanceaw2=bankingServies.withdrawAmount(customer1, account12, 100, pin2);
			System.out.println(totalBalanceaw2);
			float totalBalanceaw3=bankingServies.withdrawAmount(customer1, account13, 100, pin3);
			System.out.println(totalBalanceaw3);
			boolean a=bankingServies.fundTransfer(customer1, account12, customer1, account11, 500000, pin1);
			System.out.println(a);
			System.out.println(bankingServies.getAccountDetails(customer1, account11).getAccountBalance());
			boolean b=bankingServies.fundTransfer(customer1, account13, customer1, account12, 5000, pin2);
			System.out.println(b);
			System.out.println(bankingServies.getAccountDetails(customer1, account12).getAccountBalance());

			int newpin1=bankingServies.generateNewPin(customer1, account11);
			System.out.println(newpin1);

			Transaction[] transaction=bankingServies.getAccountAllTransaction(customer1, account11);
			for(int i=0;i<transaction.length;i++) {
				System.out.println(transaction[i]);
			}

			Customer[] customer=bankingServies.getAllCustomerDetails();
			for(int i=0;i<customer.length;i++) {
				System.out.println(customer[i]);
			}*/


