package com.cg.payroll.services;

import java.util.List;

import com.cg.payroll.beans.Associate;
import com.cg.payroll.exceptions.AssociateDetailsNotFoundExceptions;
import com.cg.payroll.exceptions.PayrollServicesDownExceptions;

public interface PayrollServices {

	int acceptAssociateDetails(Associate associate) throws PayrollServicesDownExceptions;

	float calculateNetSalary(int associateId) throws AssociateDetailsNotFoundExceptions,PayrollServicesDownExceptions;

	Associate getAssociateDetails(int associateId) throws AssociateDetailsNotFoundExceptions,PayrollServicesDownExceptions;

	List<Associate> getAllAssociateDetails()throws PayrollServicesDownExceptions;
	
	boolean deleteAssociate(int associateId)throws PayrollServicesDownExceptions;
	
	boolean updateAssociate(Associate associate)throws PayrollServicesDownExceptions;
}