package com.cg.payroll.exceptionacpect;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import com.cg.payroll.beans.Associate;
import com.cg.payroll.exceptions.AssociateDetailsNotFoundException;
import com.cg.payroll.exceptions.PayrollServicesDownException;


@ControllerAdvice(basePackages={"com.cg.payroll.controllers"})
public class PayrollExceptionAspectController {	
	
	@ExceptionHandler (AssociateDetailsNotFoundException.class)
	public ModelAndView handleUserDetailsNotFoundException(AssociateDetailsNotFoundException ex){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("loginPage");
		modelAndView.addObject("associate", new Associate());
		modelAndView.addObject("exceptionMsg", ex.getMessage());
		return modelAndView;
	}
	@ExceptionHandler (PayrollServicesDownException.class)
	public String handleProjectServicesNotFoundException(PayrollServicesDownException ex){
		return "errorPage";
	}
}
