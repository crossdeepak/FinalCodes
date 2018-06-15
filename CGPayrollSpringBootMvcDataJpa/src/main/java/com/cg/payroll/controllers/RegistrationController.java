package com.cg.payroll.controllers;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import com.cg.payroll.beans.Associate;
import com.cg.payroll.exceptions.PayrollServicesDownException;
import com.cg.payroll.services.PayrollServices;

@Controller
public class RegistrationController {
	
	@Autowired 
		PayrollServices payrollServices;

	@RequestMapping(value="/registerUser")
	public String registerUser(@Valid @ModelAttribute("associate") Associate associate,
			BindingResult result) throws PayrollServicesDownException {
			if(result.hasErrors())return "registrationPage";
			
			throw new PayrollServicesDownException();
			/*payrollServices.acceptAssociateDetails(associate);
			return "successPage";*/
		
	}
	
}
