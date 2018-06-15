package com.cg.mobilebilling.client;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.cg.mobilebilling.exceptions.BillDetailsNotFoundException;
import com.cg.mobilebilling.exceptions.BillingServicesDownException;
import com.cg.mobilebilling.exceptions.CustomerDetailsNotFoundException;
import com.cg.mobilebilling.exceptions.InvalidBillMonthException;
import com.cg.mobilebilling.exceptions.PlanDetailsNotFoundException;
import com.cg.mobilebilling.exceptions.PostpaidAccountNotFoundException;
import com.cg.mobilebilling.services.BillingServices;

public class MainClass {
	public static void main(String[] args) throws BillingServicesDownException, PlanDetailsNotFoundException, CustomerDetailsNotFoundException, PostpaidAccountNotFoundException, InvalidBillMonthException, BillDetailsNotFoundException{
		ApplicationContext applicationContext=new ClassPathXmlApplicationContext("projectbeans.xml");
		BillingServices billingServices=(BillingServices) applicationContext.getBean("billingServices");
		billingServices.acceptCustomerDetails("Deepak", "Kumar", "acs@bmc.com", "18/08/1994", "dpg", "short road", 713204, "dgg", "dasd", 713213);
		billingServices.acceptCustomerDetails("Rahul", "Kumar", "acs@bmc.com", "12/06/1994", "dpg", "short road", 713204, "dgg", "dasd", 713213);
		billingServices.acceptCustomerDetails("Cross", "Fire", "acs@bmc.com", "18/08/1994", "dpg", "short road", 713204, "dgg", "dasd", 713213);
		billingServices.createPlan(100, 100, 100, 100, 100, 100, 1, 1.5f, 1, 1.5f, 2, "Durgapur", "LOW");
		billingServices.createPlan(200, 100, 100, 100, 100, 100, 2, 2.5f, 2, 2.5f, 4, "Kolkata", "MID");
		billingServices.createPlan(300, 100, 100, 100, 100, 100, 4, 4.5f, 4, 4.5f, 8, "Asansol", "HIGH");
		billingServices.openPostpaidMobileAccount(1, 2);
		billingServices.openPostpaidMobileAccount(3, 3);
		billingServices.openPostpaidMobileAccount(3, 2);
		System.out.println(billingServices.getAllCustomerDetails());
		System.out.println(billingServices.getPostPaidAccountDetails(3, 2));
		System.out.println(billingServices.getCustomerAllPostpaidAccountsDetails(3));
		System.out.println(billingServices.changePlan(2, 1, 3));
		System.out.println(billingServices.getCustomerPostPaidAccountPlanDetails(2, 1));
		System.out.println(billingServices.generateMonthlyMobileBill(1, 1, "FEB", 110, 110, 110, 110, 110));
		System.out.println(billingServices.generateMonthlyMobileBill(3, 3, "FEB", 110, 110, 110, 110, 110));
		System.out.println(billingServices.generateMonthlyMobileBill(3, 3, "MAR", 120, 120, 120, 120, 120));
		System.out.println(billingServices.getCustomerPostPaidAccountAllBillDetails(3, 3));
//		System.out.println(billingServices.getMobileBillDetails(1, 1, "FEB"));
//		billingServices.deleteCustomer(1);
//		billingServices.closeCustomerPostPaidAccount(1, 1);
	}
}