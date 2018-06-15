package com.cg.payroll.exceptionaspects;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import com.cg.payroll.beans.Associate;
import com.cg.payroll.exceptions.AssociateDetailsNotFoundExceptions;
import com.cg.payroll.exceptions.PayrollServicesDownExceptions;

@ControllerAdvice(basePackages="com.cg.payroll.controller")
public class ExceptionAspect {
	
	@ExceptionHandler(value=PayrollServicesDownExceptions.class)
	public String PayrollDownException(){
		return "errorPage";
	}
	
	@ExceptionHandler(value=AssociateDetailsNotFoundExceptions.class)
	public ModelAndView HandleAssociateDetailsNotFound(AssociateDetailsNotFoundExceptions ex){
		ModelAndView model=new ModelAndView();
		model.setViewName("loginPage");
		model.addObject("associate", new Associate());
		return model;
	}
}
