package com.cg.payroll.controllers;
import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;
import com.cg.payroll.beans.Associate;
@Controller
public class LoginController {
	public ModelAndView authUser(@Valid @ModelAttribute("associate")Associate associate) {
		return null;
	}
}
