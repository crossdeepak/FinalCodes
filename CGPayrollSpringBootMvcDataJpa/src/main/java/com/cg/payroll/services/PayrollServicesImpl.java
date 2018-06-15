package com.cg.payroll.services;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.cg.payroll.beans.Associate;
import com.cg.payroll.beans.BankDetails;
import com.cg.payroll.beans.Salary;
import com.cg.payroll.daoservices.PayrollDAOServices;
import com.cg.payroll.exceptions.AssociateDetailsNotFoundException;
import com.cg.payroll.exceptions.PayrollServicesDownException;
@Component(value="payrollServices")
public class PayrollServicesImpl implements PayrollServices {
	
	@Autowired
	private PayrollDAOServices payrollDAOServices ;

	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public int acceptAssociateDetails(Associate associate) throws PayrollServicesDownException {
		return payrollDAOServices.save(associate).getAssociateId();	
	}

	@Transactional
	@Override
	public int calculateNetSalary(int associateId)
			throws AssociateDetailsNotFoundException, PayrollServicesDownException {
		Associate associate= getAssociateDetails(associateId);
		
		associate.setFirstName("NewName");
		associate.setBankDetails(new BankDetails(11111, "ABC Corp", "abc22"));
		payrollDAOServices.saveAndFlush(associate);
		return 0;
	}

	@Transactional
	@Override
	public Associate getAssociateDetails(int associateId)
			throws AssociateDetailsNotFoundException, PayrollServicesDownException {
		Associate associate =payrollDAOServices.findById(associateId).orElse(null); 
		if(associate==null)throw new AssociateDetailsNotFoundException("Associate details with Id "+associateId+" not found");
		return associate;
	}

	@Transactional
	@Override
	public List<Associate> getAllAssociatesDetails() throws PayrollServicesDownException {
		return payrollDAOServices.findAll();
	}
	
}
