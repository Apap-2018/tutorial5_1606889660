package com.apap.tutorial5.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.apap.tutorial5.model.FlightModel;
import com.apap.tutorial5.model.PilotModel;
import com.apap.tutorial5.service.FlightService;
import com.apap.tutorial5.service.PilotService;

@Controller
public class FlightController {
	@Autowired
	private FlightService flightService;
	
	@Autowired
	private PilotService pilotService;
	
	@RequestMapping(value = "/flight/add/{licenseNumber}", method = RequestMethod.GET)
	private String add(@PathVariable(value = "licenseNumber") String licenseNumber, Model model) {
		PilotModel pilot = pilotService.getPilotDetailByLicenseNumber(licenseNumber);
	
		List<FlightModel> flightList = new ArrayList<>();
		flightList.add(new FlightModel());
		pilot.setPilotFlight(flightList);
		model.addAttribute("pilot", pilot);
		model.addAttribute("title", "Add flights");
		return "addFlight";
	}
	
	@RequestMapping(value = "/flight/add", method = RequestMethod.POST)
	private String addFlightSubmit(@ModelAttribute FlightModel flight, Model model) {
		flightService.addFlight(flight);
		model.addAttribute("title", "Add flights");
		return "add";
	}
	
	@RequestMapping(value="/flight/add/{licenseNumber}", params= {"save"}, method=RequestMethod.POST)
	public String addFlightSubmit(@ModelAttribute PilotModel pilot) {
		PilotModel curr = pilotService.getPilotDetailByLicenseNumber(pilot.getLicenseNumber());
		List<FlightModel> flights = pilot.getPilotFlight();
		for(int i= 0; i < flights.size(); i++) {
			flights.get(i).setPilot(curr);
			System.out.println(flights.get(i).getFlightNumber());
			flightService.addFlight(flights.get(i));
		}
		
		return "add";
	}
	
	@RequestMapping(value="/flight/add/{licenseNumber}", params= {"addRow"}, method=RequestMethod.POST)
	private String addRow(@ModelAttribute PilotModel pilot, Model model) {
		pilot.getPilotFlight().add(new FlightModel());
		model.addAttribute("pilot", pilot);
		return "addFlight";
	}
	
	
	
	
	
	@RequestMapping(value = "/flight/{licenseNumber}/update/{id}", method = RequestMethod.GET)
	private String updateFlight(@PathVariable(value = "id") String id,@PathVariable(value = "licenseNumber") String licenseNumber, Model model) {
		FlightModel flight = flightService.getFlightDetailByIdAndPilotLicenseNumber(Long.parseLong(id), licenseNumber);
		PilotModel pilot = pilotService.getPilotDetailByLicenseNumber(licenseNumber);
		flight.setPilot(pilot);
		model.addAttribute("licenseNumber", licenseNumber);
		model.addAttribute("flight", flight);
		model.addAttribute("title", "Update flights");
		return "updateFlight";
	}
	
	@RequestMapping(value = "/flight/update/", method = RequestMethod.POST)
	private String updateFlightSubmit(@ModelAttribute FlightModel flight, Model model) {
		
		flightService.update(flight);
		model.addAttribute("title", "Update flights");
		return "update";
	}
	
	/*@RequestMapping(value="/pilot/view")
	private String pilotView(@RequestParam(value = "licenseNumber", required = true) String licenseNumber, Model model) {
		model.addAttribute("pilot", pilotService.getPilotDetailByLicenseNumber(licenseNumber));
		List<FlightModel> flights = flightService.getFlightDetailByLicenseNumber(licenseNumber);
		model.addAttribute("flights", flights);
		return "view-pilot";
	}*/
	
	/*@RequestMapping(value = "/flight/delete/{id}", method = RequestMethod.GET)
	private String deleteFlight(@PathVariable(value = "id") String id) {
		flightService.removeById(Long.parseLong(id));
		return "delete";
	}*/
	
	@RequestMapping(value = "/flight/delete", method = RequestMethod.POST)
	private String deleteFlight(@ModelAttribute PilotModel pilot, Model model) {
		for(FlightModel flight: pilot.getPilotFlight()) {
			flightService.removeById(flight.getId());
		}
		model.addAttribute("title", "Delete flights");
		return "delete";
	}
	
	@RequestMapping(value = "/flight/viewAll")
	private String viewFlights(Model model) {
		List<FlightModel> flights = flightService.getAllFlights();
		model.addAttribute("flights", flights);
		model.addAttribute("title", "View flights");
		return "viewFlights";
	}
	
	
	
	
}
