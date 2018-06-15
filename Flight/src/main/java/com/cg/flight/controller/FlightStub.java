package com.cg.flight.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cg.flight.model.Flight;
import com.cg.flight.model.Plane;

public class FlightStub {
	private static Map<Integer, Flight> flights=new HashMap<Integer, Flight>();
	private static int idIndex=3;
	
	static {
		Flight a=new Flight(1, 2000, 1, "ECR20", "27/08/2018", "KOL", "PUN", new Plane("Boing 737", 150));
		flights.put(1, a);
		Flight b=new Flight(2, 2000, 12, "EJR20", "12/09/2018", "PUN", "MUM", new Plane("Boing 317", 120));
		flights.put(2, b);
		Flight c=new Flight(3, 2000, 15, "ECR20", "05/07/2018", "MUM", "PUN", new Plane("Boing 337", 140));
		flights.put(3, c);
	}
	
	public static List<Flight>list(){
		return new ArrayList<Flight>(flights.values());
	}
	public static Flight create(Flight flight) {
		idIndex=idIndex+1;
		flight.setId(idIndex);
		flights.put(idIndex, flight);
		return flight;
	}
	public static Flight get(int id) {
		return flights.get(id);
	}
	public static Flight update(int id, Flight flight) {
		flights.put(id, flight);
		return flight;}
	public static Flight delete(int id) {
		return flights.remove(id);
		}
}
