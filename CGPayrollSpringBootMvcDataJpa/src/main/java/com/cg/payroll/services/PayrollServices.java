package com.cg.payroll.services;

import java.util.List;
import com.cg.payroll.beans.Associate;
import com.cg.payroll.exceptions.AssociateDetailsNotFoundException;
import com.cg.payroll.exceptions.PayrollServicesDownException;

public interface PayrollServices {

	int acceptAssociateDetails(Associate associate) throws PayrollServicesDownException;

	int calculateNetSalary(int associateId)throws AssociateDetailsNotFoundException, PayrollServicesDownException;

	Associate getAssociateDetails(int associateId)throws AssociateDetailsNotFoundException , PayrollServicesDownException;

	List<Associate> getAllAssociatesDetails()throws PayrollServicesDownException;

}