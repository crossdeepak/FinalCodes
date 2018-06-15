package com.cg.payroll.main;

import java.io.IOException;
import java.sql.JDBCType;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import com.cg.payroll.beans.Associate;
import com.cg.payroll.beans.BankDetails;
import com.cg.payroll.beans.Salary;
import com.cg.payroll.services.PayrollServices;
import com.cg.payroll.services.PayrollServicesImpl;
import com.cg.payroll.utilities.PayrollUtility;
import com.cg.payroll.exception.AssociateDetailsNotFoundException;
import com.cg.payroll.exception.PayrollServicesDownException;

public class MainClass {

	public static void main(String[] args) throws PayrollServicesDownException, SQLException, AssociateDetailsNotFoundException {	
		ApplicationContext applicationContext=new ClassPathXmlApplicationContext("projectbeans.xml");
		PayrollServices payrollServices=(PayrollServices)applicationContext.getBean("payrollServices");
		System.out.println(payrollServices.acceptAssociateDetails("Cross", "Fire", "a.com", "java", "analyst", "e688", 66000, 250000, 13000, 11000, 3766, "citi", "citi501"));
		System.out.println(payrollServices.acceptAssociateDetails("Davy", "Jones", "a.com", "java", "analyst", "e688", 80000, 200000, 15000, 17000, 3426, "icici", "icici111"));
		System.out.println(payrollServices.acceptAssociateDetails("VP", "TaZ", "a.com", "java", "analyst", "e688", 75000, 300000, 17000, 13000, 3466, "hdfc", "hdfc101"));
		payrollServices.updateAssociateDetails(new Associate(2, 90000, "Deepak", "Kumar", "te", "few", "dew", "chew", new Salary(20000, 2000, 1000, 2000, 1000, 13000, 2000, 1000, 500, 30000, 40000), new BankDetails(1231241, "citi", "citi123")));
//		payrollServices.deleteAssociateDetails(1);
		payrollServices.calculateNetSalary(1);
		payrollServices.calculateNetSalary(2);
		payrollServices.calculateNetSalary(3);
		System.out.println(payrollServices.getAllAssociateDetails());
		System.out.println(payrollServices.getAssociateDetails(2));
		 
	}
}