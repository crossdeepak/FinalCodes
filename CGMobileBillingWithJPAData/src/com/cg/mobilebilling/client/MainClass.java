package com.cg.mobilebilling.client;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.cg.mobilebilling.exceptions.BillingServicesDownException;
import com.cg.mobilebilling.exceptions.CustomerDetailsNotFoundException;
import com.cg.mobilebilling.exceptions.PlanDetailsNotFoundException;
import com.cg.mobilebilling.services.BillingServices;

public class MainClass {
	public static void main(String[] args) throws BillingServicesDownException, PlanDetailsNotFoundException, CustomerDetailsNotFoundException{
		ApplicationContext applicationContext=new ClassPathXmlApplicationContext("projectbeans.xml");
		BillingServices billingServices=(BillingServices) applicationContext.getBean("billingServices");
		billingServices.acceptCustomerDetails("Deepak", "Kumar", "acs@bmc.com", "18/08/1994", "dpg", "short road", 713204, "dgg", "dasd", 713213);
		billingServices.acceptCustomerDetails("Rahul", "Kumar", "acs@bmc.com", "12/06/1994", "dpg", "short road", 713204, "dgg", "dasd", 713213);
		billingServices.acceptCustomerDetails("Cross", "Fire", "acs@bmc.com", "18/08/1994", "dpg", "short road", 713204, "dgg", "dasd", 713213);
		billingServices.createPlan(100, 100, 50, 50, 50, 100, 50, 100, 30, 40, 200, "Durgapur", "LOW");
		billingServices.createPlan(100, 100, 100, 200, 200, 100, 50, 100, 30, 40, 200, "Durgapur", "MID");
		billingServices.createPlan(100, 100, 50, 500, 500, 100, 500, 100, 300, 40, 200, "Durgapur", "HIGH");
		billingServices.openPostpaidMobileAccount(1, 2);
		System.out.println(billingServices.getAllCustomerDetails());
	}
}