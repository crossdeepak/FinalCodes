package com.cg.mobilebilling.services;

import java.util.ArrayList;
import java.util.List;

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
		customer=daoServicesCustomer.findOne(customerID);
		plan=daoServicesPlan.findOne(planID);
		postPaidAccount=new PostpaidAccount(plan);
		postPaidAccount.setCustomer(customer);
		postPaidAccount.setPlan(plan);
		daoServicesPostAccount.save(postPaidAccount);
		return 0;
	}

	@Override
	public int generateMonthlyMobileBill(int customerID, long mobileNo, String billMonth, int noOfLocalSMS,
			int noOfStdSMS, int noOfLocalCalls, int noOfStdCalls, int internetDataUsageUnits)
			throws CustomerDetailsNotFoundException, PostpaidAccountNotFoundException, InvalidBillMonthException,
			BillingServicesDownException, PlanDetailsNotFoundException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Customer getCustomerDetails(int customerID)
			throws CustomerDetailsNotFoundException, BillingServicesDownException {
		if(daoServicesCustomer.findOne(customerID)==null)
			throw new CustomerDetailsNotFoundException("CustomerID : "+customerID+" not found.");
		return daoServicesCustomer.findOne(customerID);
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
		if(getCustomerDetails(customerID).getCustomerID()==customerID)
		return daoServicesPostAccount.findOne(mobileNo);
		return null;
	}

	@Override
	public List<PostpaidAccount> getCustomerAllPostpaidAccountsDetails(int customerID)
			throws CustomerDetailsNotFoundException, BillingServicesDownException {
		// TODO Auto-generated method stub
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
		if(!daoServicesPostAccount.findOne(mobileNo).getBills().get(billMonth).equals(billMonth))
			throw new InvalidBillMonthException("Bill details not available for this month.");
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
		if(getCustomerDetails(customerID).getCustomerID()==customerID &&
				getPostPaidAccountDetails(customerID, mobileNo).getMobileNo()==mobileNo){
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
		if(getCustomerDetails(customerID).getCustomerID()==customerID &&
				getPostPaidAccountDetails(customerID, mobileNo).getMobileNo()==mobileNo &&
				getCustomerPostPaidAccountPlanDetails(customerID, mobileNo).getPlanID()==planID){
			plan=daoServicesPlan.findOne(planID);
			postPaidAccount=new PostpaidAccount(plan);
			postPaidAccount.setPlan(plan);
			daoServicesPostAccount.saveAndFlush(postPaidAccount);
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
		if(postPaidAccount.getCustomer().getCustomerID()==customerID &&
				getPostPaidAccountDetails(customerID, mobileNo).getMobileNo()==mobileNo){
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
		if(customer.getCustomerID()==customerID){
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
		if(customer.getCustomerID()==customerID)
		return daoServicesPostAccount.findOne(mobileNo).getPlan();
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