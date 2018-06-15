package com.cg.payroll.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.cg.payroll.beans.Associate;
import com.cg.payroll.beans.Salary;
import com.cg.payroll.exceptions.AssociateDetailsNotFoundExceptions;
import com.cg.payroll.exceptions.PayrollServicesDownExceptions;
import com.cg.payroll.services.PayrollServices;

@Controller
public class RegistrationController {
	
	@Autowired
	PayrollServices payrollServices;
	
	@RequestMapping(value="/registerUser")
	public String registerUser(@Valid @ModelAttribute("associate")Associate associate, BindingResult result) throws PayrollServicesDownExceptions {
				if(result.hasErrors())return "registrationPage";
				payrollServices.acceptAssociateDetails(associate);
				return "successPage";
		}
	@RequestMapping(value="/salaryCalculate")
	public ModelAndView salaryCalculate(@ Valid @ModelAttribute("associate")Associate associate, BindingResult result) throws AssociateDetailsNotFoundExceptions, PayrollServicesDownExceptions {
		
			/*Float netSal=payrollServices.calculateNetSalary(associate.getAssociateID());
			Salary salary=new Salary();
			salary.setNetSalary(netSal);
			associate.setSalary(salary);
			return "salaryDisplay";*/
		
		ModelAndView modelAndView=new ModelAndView();
		/*if(result.hasFieldErrors("associateID")){
			modelAndView.setViewName("salaryCalculation");
			return modelAndView;
		}*/
		payrollServices.calculateNetSalary(associate.getAssociateID());
		Associate associate2=payrollServices.getAssociateDetails(associate.getAssociateID());
		modelAndView.addObject("associate", associate2);
		modelAndView.setViewName("salaryDisplay");
		return modelAndView;
	}
	@RequestMapping(value="/loginUser")
	public ModelAndView loginUser(@Valid @ModelAttribute("associate")Associate associate, BindingResult result) throws AssociateDetailsNotFoundExceptions, PayrollServicesDownExceptions {
			/*Associate associate2=payrollServices.getAssociateDetails(associate.getAssociateID());
			if(associate.getAssociateID()==payrollServices.getAssociateDetails(associate.getAssociateID()).getAssociateID()){
			associate.setFirstName(associate2.getFirstName());
			associate.setLastName(associate2.getLastName());
			}
				return "loginDisplay";*/
		
		ModelAndView modelAndView=new ModelAndView();
		if(result.hasFieldErrors("password")){
			modelAndView.setViewName("loginPage");
			return modelAndView;
		}
		Associate associate2=payrollServices.getAssociateDetails(associate.getAssociateID());
		if(associate.getAssociateID()==associate2.getAssociateID()
				&& associate.getPassword().equals(associate2.getPassword())){
			modelAndView.addObject("associate", associate2);
			modelAndView.setViewName("loginDisplay");
			return modelAndView;
		}
		modelAndView.setViewName("loginPage");
		return modelAndView;
	}
}
