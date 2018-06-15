package com.cg;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cg.dao.CarDAO;
import com.cg.dto.CarDTO;

@Controller
public class MyController {

	@Autowired
	private CarDAO carDAO;

	@RequestMapping("/viewCars")
	String viewAllCars(ModelMap map){
		List<CarDTO> cars = carDAO.findAll();
		
		map.addAttribute("carList",cars);
		System.out.println(cars);
		return "carList";
	}
	
}
