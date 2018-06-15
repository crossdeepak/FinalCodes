package com.cg.payroll.services;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cg.payroll.beans.Associate;
import com.cg.payroll.beans.BankDetails;
import com.cg.payroll.beans.Salary;
import com.cg.payroll.daoservices.PayrollDAOServices;
import com.cg.payroll.exception.AssociateDetailsNotFoundException;
import com.cg.payroll.exception.PayrollServicesDownException;

@Component("payrollServices")
@Transactional
public class PayrollServicesImpl implements PayrollServices {
	@Autowired
	private PayrollDAOServices daoServices;
//	public PayrollServicesImpl() throws PayrollServicesDownException{
//		daoServices=new PayrollDAOServicesImpl();
//	}

	@Override
	public int acceptAssociateDetails(String firstName,String lastName,String emailId,String department,
			String designation,String pancard,int yearlyInvestmentUnder80C,
			float basicSalary,float epf,float companyPf,int accountNumber,String bankName,String ifscCode)
					throws PayrollServicesDownException{
		Associate associate=daoServices.save(new Associate(yearlyInvestmentUnder80C, 
				firstName, lastName, department, designation, pancard, emailId,
				new Salary(basicSalary, epf, companyPf),
				new BankDetails(accountNumber, bankName, ifscCode)));
		return associate.getAssociateID();
	}

	@Override
	public float calculateNetSalary(int associateID)
			throws AssociateDetailsNotFoundException, PayrollServicesDownException{
		Associate associate=daoServices.findOne(associateID);
		if(associate!=null){
			associate.getSalary().setOtherAllowance((float)(0.1)*(associate.getSalary().getBasicSalary()));
			associate.getSalary().setConveyenceAllowance((float)(0.2)*(associate.getSalary().getBasicSalary()));
			associate.getSalary().setPersonalAllowance((float)(0.3)*(associate.getSalary().getBasicSalary()));
			associate.getSalary().setGratuity((float)(0.05)*(associate.getSalary().getBasicSalary()));
			associate.getSalary().setHra((float)(0.25)*(associate.getSalary().getBasicSalary()));
			associate.getSalary().setGrossSalary((float)(associate.getSalary().getOtherAllowance()
					+associate.getSalary().getConveyenceAllowance()
					+associate.getSalary().getPersonalAllowance()
					+associate.getSalary().getHra()+associate.getSalary().getCompanyPf()
					+associate.getSalary().getBasicSalary()));
			float annualSalary=associate.getSalary().getGrossSalary()*12;
			float investmentValue=associate.getYearlyInvestmentUnder80C()
					+associate.getSalary().getEpf()+associate.getSalary().getCompanyPf();
			float taxCalculated=0;
			if(investmentValue>150000)
				investmentValue=150000;
			if(annualSalary<250000)
				taxCalculated=0;
			else if(annualSalary>250000&&annualSalary<500000){
				if((annualSalary-250000-investmentValue)<0)
					taxCalculated=0;
				else
					taxCalculated=(float) ((annualSalary-250000-investmentValue)*0.1);
			}
			else if(annualSalary>500000&&annualSalary<1000000)
				taxCalculated=(float) ((250000-investmentValue)*0.1+(annualSalary-500000)*0.2);
			else if(annualSalary>1000000)
				taxCalculated=(float) ((250000-investmentValue)*0.1+500000*0.2+(annualSalary-1000000)*0.3);
			associate.getSalary().setMonthlyTax(taxCalculated/12);
			float netSalary=associate.getSalary().getGrossSalary()-associate.getSalary().getMonthlyTax()
					-associate.getSalary().getEpf()-associate.getSalary().getCompanyPf();
			associate.getSalary().setNetSalary(netSalary);
			daoServices.saveAndFlush(associate);
			return netSalary;
		}
		return 0;
	}

	@Override
	public boolean deleteAssociateDetails(int associateID)throws PayrollServicesDownException{
		daoServices.delete(daoServices.findOne(associateID));
		return true;
	}
	@Override
	public boolean updateAssociateDetails(Associate associate) throws PayrollServicesDownException {
		daoServices.saveAndFlush(associate);
		return true;
	}


	public Associate getAssociateDetails(int associateID)
			throws AssociateDetailsNotFoundException, PayrollServicesDownException{
		Associate associate=daoServices.findOne(associateID);
		if(associate==null){
			AssociateDetailsNotFoundException ex=
					new AssociateDetailsNotFoundException("Associate details not found for "+associateID);
			throw ex;
		}
		return associate;
	}

	@Override
	public List<Associate> getAllAssociateDetails()throws PayrollServicesDownException{
		return daoServices.findAll();
	}


}
