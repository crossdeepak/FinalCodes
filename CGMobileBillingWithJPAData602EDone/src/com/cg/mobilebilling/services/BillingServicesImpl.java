package com.cg.mobilebilling.services;

import java.util.ArrayList;
import java.util.List;
import java.lang.String;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cg.mobilebilling.beans.Address;
import com.cg.mobilebilling.beans.Bill;
import com.cg.mobilebilling.beans.Customer;
import com.cg.mobilebilling.beans.Plan;
import com.cg.mobilebilling.beans.PostpaidAccount;
import com.cg.mobilebilling.daoservices.BillingDAOServicesBill;
import com.cg.mobilebilling.daoservices.BillingDAOServicesCustomer;
import com.cg.mobilebilling.daoservices.BillingDAOServicesPlan;
import com.cg.mobilebilling.daoservices.BillingDAOServicesPostAccount;
import com.cg.mobilebilling.exceptions.BillDetailsNotFoundException;
import com.cg.mobilebilling.exceptions.BillingServicesDownException;
import com.cg.mobilebilling.exceptions.CustomerDetailsNotFoundException;
import com.cg.mobilebilling.exceptions.InvalidBillMonthException;
import com.cg.mobilebilling.exceptions.PlanDetailsNotFoundException;
import com.cg.mobilebilling.exceptions.PostpaidAccountNotFoundException;

@Component("billingServices")
public class BillingServicesImpl implements BillingServices {
	@Autowired
	private BillingDAOServicesCustomer daoServicesCustomer;
	@Autowired
	private BillingDAOServicesPostAccount daoServicesPostAccount;
	@Autowired
	private BillingDAOServicesPlan daoServicesPlan;
	@Autowired
	private BillingDAOServicesBill daoServicesBill;
	Customer customer;
	PostpaidAccount postPaidAccount;
	Bill bill;
	Address address;
	Plan plan;
	
	@Override
	public List<Plan> getPlanAllDetails() throws BillingServicesDownException {
		return daoServicesPlan.findAll();
	}

	@Override
	public int acceptCustomerDetails(String firstName, String lastName, String emailID, String dateOfBirth,
			String billingAddressCity, String billingAddressState, int billingAddressPinCode, String homeAddressCity,
			String homeAddressState, int homeAddressPinCode) throws BillingServicesDownException {
		customer=daoServicesCustomer.save(new Customer(firstName, lastName, emailID, dateOfBirth, new Address(billingAddressPinCode, billingAddressCity, billingAddressState), new Address(homeAddressPinCode, homeAddressCity, homeAddressState)));
		return customer.getCustomerID();
	}

	@Override
	public long openPostpaidMobileAccount(int customerID, int planID)
			throws PlanDetailsNotFoundException, CustomerDetailsNotFoundException, BillingServicesDownException {
		if(daoServicesCustomer.findOne(customerID)==null)
			throw new CustomerDetailsNotFoundException("CustomerID : "+customerID+" not found.");
		if(daoServicesPlan.findOne(planID)==null)
			throw new PlanDetailsNotFoundException("No such plan found.");
		if(daoServicesCustomer.findOne(customerID)!=null && daoServicesPlan.findOne(planID)!=null){
		customer=daoServicesCustomer.findOne(customerID);
		plan=daoServicesPlan.findOne(planID);
		postPaidAccount=new PostpaidAccount(plan);
		postPaidAccount.setCustomer(customer);
		postPaidAccount.setPlan(plan);
		daoServicesPostAccount.save(postPaidAccount);
		return postPaidAccount.getMobileNo();
		}
		return 0;
	}

	@Override
	public int generateMonthlyMobileBill(int customerID, long mobileNo, String billMonth, int noOfLocalSMS,
			int noOfStdSMS, int noOfLocalCalls, int noOfStdCalls, int internetDataUsageUnits)
			throws CustomerDetailsNotFoundException, PostpaidAccountNotFoundException, InvalidBillMonthException,
			BillingServicesDownException, PlanDetailsNotFoundException {
		if(daoServicesCustomer.findOne(customerID)==null)
			throw new CustomerDetailsNotFoundException("CustomerID : "+customerID+" not found.");
		if(daoServicesPostAccount.findOne(mobileNo)==null)
			throw new PostpaidAccountNotFoundException("Invalid Mobile Number.");
		if(daoServicesPostAccount.findOne(mobileNo).getPlan()==null)
			throw new PlanDetailsNotFoundException("No such plan found.");
		if(!"JAN".equals(billMonth)&&!"FEB".equals(billMonth)&&
				!"MAR".equals(billMonth)&&!"APR".equals(billMonth)&&
				!"MAY".equals(billMonth)&&!"JUN".equals(billMonth)&&
				!"JUL".equals(billMonth)&&!"AUG".equals(billMonth)&&
				!"SEP".equals(billMonth)&&!"OCT".equals(billMonth)&&
				!"NOV".equals(billMonth)&&!"DEC".equals(billMonth))
			throw new InvalidBillMonthException("Enter Correct Month. (Format 'JAN','FEB' and so on!)");
		int extraLocalCalls;
		int extraStdCalls;
		int extraLocalSMS;
		int extraStdSMS;
		int extraInternetUsage;
		float billEstimate;
		if(daoServicesCustomer.findOne(customerID).getPostpaidAccounts().get(mobileNo)!=null){
			bill=new Bill(noOfLocalSMS, noOfStdSMS, noOfLocalCalls, noOfStdCalls, internetDataUsageUnits, billMonth);
			int planId=daoServicesPostAccount.findOne(mobileNo).getPlan().getPlanID();
			plan=daoServicesPlan.findOne(planId);
			postPaidAccount=daoServicesPostAccount.findOne(mobileNo);
			if(plan.getFreeLocalCalls()<noOfLocalCalls)
				extraLocalCalls=noOfLocalCalls-plan.getFreeLocalCalls();
			else
			extraLocalCalls=0;
			System.out.println(extraLocalCalls);
			bill.setLocalCallAmount(extraLocalCalls*plan.getLocalCallRate());
			if(plan.getFreeStdCalls()<noOfStdCalls)
				extraStdCalls=noOfStdCalls-plan.getFreeStdCalls();
			else
			extraStdCalls=0;
			bill.setStdCallAmount(extraStdCalls*plan.getStdCallRate());
			if(plan.getFreeLocalSMS()<noOfLocalSMS)
				extraLocalSMS=noOfLocalSMS-plan.getFreeLocalSMS();
			else
			extraLocalSMS=0;
			bill.setLocalSMSAmount(extraLocalSMS*plan.getLocalSMSRate());
			if(plan.getFreeStdSMS()<noOfStdSMS)
				extraStdSMS=noOfStdSMS-plan.getFreeStdSMS();
			else
			extraStdSMS=0;
			bill.setStdSMSAmount(extraStdSMS*plan.getLocalSMSRate());
			if(plan.getFreeInternetDataUsageUnits()<internetDataUsageUnits)
				extraInternetUsage=internetDataUsageUnits-plan.getFreeInternetDataUsageUnits();
			else
			extraInternetUsage=0;
			bill.setInternetDataUsageAmount(extraInternetUsage*plan.getInternetDataUsageRate());
			billEstimate=plan.getMonthlyRental()+bill.getInternetDataUsageAmount()+bill.getLocalCallAmount()+bill.getLocalSMSAmount()
					+bill.getStdCallAmount()+bill.getStdSMSAmount();
			bill.setServicesTax((billEstimate*14)/100);
			bill.setVat((billEstimate*9)/100);
			bill.setTotalBillAmount(billEstimate+bill.getServicesTax()+bill.getVat());
			bill.setPostpaidAccount(postPaidAccount);
			System.out.println(bill.getTotalBillAmount());
			daoServicesBill.save(bill);
			return bill.getBillID();
		}
		return 0;
	}

	@Override
	public Customer getCustomerDetails(int customerID)
			throws CustomerDetailsNotFoundException, BillingServicesDownException {
		if(daoServicesCustomer.findOne(customerID)==null)
			throw new CustomerDetailsNotFoundException("CustomerID : "+customerID+" not found.");
		if(daoServicesCustomer.findOne(customerID)!=null)
		return daoServicesCustomer.findOne(customerID);
		return null;
	}

	@Override
	public List<Customer> getAllCustomerDetails() throws BillingServicesDownException {
		return daoServicesCustomer.findAll();
	}

	@Override
	public PostpaidAccount getPostPaidAccountDetails(int customerID, long mobileNo)
			throws CustomerDetailsNotFoundException, PostpaidAccountNotFoundException, BillingServicesDownException {
		if(daoServicesCustomer.findOne(customerID)==null)
			throw new CustomerDetailsNotFoundException("CustomerID : "+customerID+" not found.");
		if(daoServicesPostAccount.findOne(mobileNo)==null)
			throw new PostpaidAccountNotFoundException("Invalid Mobile Number.");
		if(daoServicesCustomer.findOne(customerID).getPostpaidAccounts().get(mobileNo)!=null)
		return daoServicesPostAccount.findOne(mobileNo);
		return null;
	}

	@Override
	public List<PostpaidAccount> getCustomerAllPostpaidAccountsDetails(int customerID)
			throws CustomerDetailsNotFoundException, BillingServicesDownException {
		if(daoServicesCustomer.findOne(customerID)==null)
			throw new CustomerDetailsNotFoundException("CustomerID : "+customerID+" not found.");
		if(daoServicesCustomer.findOne(customerID)!=null){
		List<PostpaidAccount> list=new ArrayList<>(daoServicesCustomer.findOne(customerID).getPostpaidAccounts().values());
		return list;
		}
		return null;
	}

	@Override
	public Bill getMobileBillDetails(int customerID, long mobileNo, String billMonth)
			throws CustomerDetailsNotFoundException, PostpaidAccountNotFoundException, InvalidBillMonthException,
			BillDetailsNotFoundException, BillingServicesDownException {
		if(daoServicesCustomer.findOne(customerID)==null)
			throw new CustomerDetailsNotFoundException("CustomerID : "+customerID+" not found.");
		if(daoServicesPostAccount.findOne(mobileNo)==null)
			throw new PostpaidAccountNotFoundException("Invalid Mobile Number.");
		if(daoServicesPostAccount.findOne(mobileNo).getBills()==null)
			throw new BillDetailsNotFoundException("Bill details not found.");
		if(!"JAN".equals(billMonth)&&!"FEB".equals(billMonth)&&
				!"MAR".equals(billMonth)&&!"APR".equals(billMonth)&&
				!"MAY".equals(billMonth)&&!"JUN".equals(billMonth)&&
				!"JUL".equals(billMonth)&&!"AUG".equals(billMonth)&&
				!"SEP".equals(billMonth)&&!"OCT".equals(billMonth)&&
				!"NOV".equals(billMonth)&&!"DEC".equals(billMonth))
			throw new InvalidBillMonthException("Enter Correct Month. (Format 'JAN','FEB' and so on!)");
	customer=daoServicesCustomer.findOne(customerID);
	postPaidAccount=daoServicesPostAccount.findOne(mobileNo);
		return (Bill) daoServicesPostAccount.findOne(mobileNo).getBills();
	}

	@Override
	public List<Bill> getCustomerPostPaidAccountAllBillDetails(int customerID, long mobileNo)
			throws CustomerDetailsNotFoundException, PostpaidAccountNotFoundException, BillingServicesDownException,
			BillDetailsNotFoundException {
		if(daoServicesCustomer.findOne(customerID)==null)
			throw new CustomerDetailsNotFoundException("CustomerID : "+customerID+" not found.");
		if(daoServicesPostAccount.findOne(mobileNo)==null)
			throw new PostpaidAccountNotFoundException("Invalid Mobile Number.");
		if(daoServicesPostAccount.findOne(mobileNo).getBills()==null)
			throw new BillDetailsNotFoundException("Bill details not found.");
		if(daoServicesCustomer.findOne(customerID).getPostpaidAccounts().get(mobileNo)!=null){
		customer=daoServicesCustomer.findOne(customerID);
		postPaidAccount=customer.getPostpaidAccounts().get(mobileNo);
		List<Bill> list=new ArrayList<>(postPaidAccount.getBills().values());
		return list;
		}
		return null;
	}

	@Override
	public boolean changePlan(int customerID, long mobileNo, int planID) throws CustomerDetailsNotFoundException,
			PostpaidAccountNotFoundException, PlanDetailsNotFoundException, BillingServicesDownException {
		if(daoServicesCustomer.findOne(customerID)==null)
			throw new CustomerDetailsNotFoundException("CustomerID : "+customerID+" not found.");
		if(daoServicesPostAccount.findOne(mobileNo)==null)
			throw new PostpaidAccountNotFoundException("Invalid Mobile Number.");
		if(daoServicesPlan.findOne(planID)==null)
			throw new PlanDetailsNotFoundException("No such plan found.");
		customer=daoServicesCustomer.findOne(customerID);
		if(customer.getPostpaidAccounts().get(mobileNo)!=null){
			plan=daoServicesPlan.findOne(planID);
			postPaidAccount=new PostpaidAccount(plan);
			customer.getPostpaidAccounts().get(mobileNo).setPlan(plan);
			postPaidAccount.setCustomer(customer);
			postPaidAccount.setMobileNo(mobileNo);
			daoServicesPostAccount.save(postPaidAccount);
			return true;
		}
		return false;
	}

	@Override
	public boolean closeCustomerPostPaidAccount(int customerID, long mobileNo)
			throws CustomerDetailsNotFoundException, PostpaidAccountNotFoundException, BillingServicesDownException {
		if(daoServicesCustomer.findOne(customerID)==null)
			throw new CustomerDetailsNotFoundException("CustomerID : "+customerID+" not found.");
		if(daoServicesPostAccount.findOne(mobileNo)==null)
			throw new PostpaidAccountNotFoundException("Invalid Mobile Number.");
		if(daoServicesCustomer.findOne(customerID).getPostpaidAccounts().get(mobileNo)!=null){
				daoServicesPostAccount.delete(mobileNo);
				return true;
		}
		return false;
	}

	@Override
	public boolean deleteCustomer(int customerID)
			throws BillingServicesDownException, CustomerDetailsNotFoundException {
		if(daoServicesCustomer.findOne(customerID)==null)
			throw new CustomerDetailsNotFoundException("CustomerID : "+customerID+" not found.");
		if(daoServicesCustomer.findOne(customerID)!=null){	
		daoServicesCustomer.delete(customerID);
			return true;
		}
		return false;
	}

	@Override
	public Plan getCustomerPostPaidAccountPlanDetails(int customerID, long mobileNo)
			throws CustomerDetailsNotFoundException, PostpaidAccountNotFoundException, BillingServicesDownException,
			PlanDetailsNotFoundException {
		if(daoServicesCustomer.findOne(customerID)==null)
			throw new CustomerDetailsNotFoundException("CustomerID : "+customerID+" not found.");
		if(daoServicesPostAccount.findOne(mobileNo)==null)
			throw new PlanDetailsNotFoundException("Invalid Mobile Number.");
		if(daoServicesCustomer.findOne(customerID).getPostpaidAccounts().get(mobileNo)!=null)
		return daoServicesCustomer.findOne(customerID).getPostpaidAccounts().get(mobileNo).getPlan();
		return null;
	}

	@Override
	public int createPlan(int monthlyRental, int freeLocalCalls, int freeStdCalls, int freeLocalSMS, int freeStdSMS,
			int freeInternetDataUsageUnits, float localCallRate, float stdCallRate, float localSMSRate,
			float stdSMSRate, float internetDataUsageRate, String planCircle, String planName)
			throws BillingServicesDownException {
		plan=daoServicesPlan.save(new Plan(monthlyRental, freeLocalCalls, freeStdCalls, freeLocalSMS, freeStdSMS, freeInternetDataUsageUnits, localCallRate, stdCallRate, localSMSRate, stdSMSRate, internetDataUsageRate, planCircle, planName));
		return plan.getPlanID();
	}

}