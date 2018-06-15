package com.cg.flight.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cg.flight.model.Flight;


@RequestMapping("api/v1/")
@RestController
@CrossOrigin(origins="*")
public class FlightController {
	
	
	@RequestMapping(value="flights", method=RequestMethod.POST)
	public Flight save(@RequestBody Flight flight){
		return FlightStub.create(flight);
	}
	
	@RequestMapping(value="flights",method=RequestMethod.GET)
	public List<Flight>list() {
		return FlightStub.list();
	}
	@RequestMapping(value="flights/{id}",method=RequestMethod.GET)
	public Flight get(@PathVariable("id") int id){
		return FlightStub.get(id);
	}
	@RequestMapping(value="flights/{id}",method=RequestMethod.DELETE)
	public boolean removeForId(@PathVariable("id") Integer id) {
		FlightStub.delete(id);
		return true;
	}
	@RequestMapping(value="flights/{id}",method=RequestMethod.PUT)
	public boolean updateForId(@PathVariable("id") Integer id, @RequestBody Flight flight) {
		FlightStub.update(id, flight);
		return true;
	}
}
