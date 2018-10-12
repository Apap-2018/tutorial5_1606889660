package com.apap.tutorial5.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.apap.tutorial5.model.FlightModel;
import com.apap.tutorial5.repository.FlightDB;

@Service
@Transactional
public class FlightServiceImpl implements FlightService{
	@Autowired
	private FlightDB flightDb;
	
	@Override
	public void addFlight(FlightModel flight) {
		flightDb.save(flight);
	}
	
	@Override
	public List<FlightModel> getFlightDetailByLicenseNumber(String licenseNumber) {
		return flightDb.findByPilotLicenseNumber(licenseNumber);
	}

	@Override
	public void removeById(Long id) {
		flightDb.deleteById(id);
	}

	@Override
	public void update(FlightModel newFlight) {
		FlightModel flight = flightDb.findById(newFlight.getId()).get();
		flight.setDestination(newFlight.getDestination());
		flight.setOrigin(newFlight.getOrigin());
		flight.setFlightNumber(newFlight.getFlightNumber());
		flight.setTime(newFlight.getTime());
		flightDb.save(flight);
		
	}

	@Override
	public FlightModel getFlightDetailByIdAndPilotLicenseNumber(long id, String licenseNumber) {
		return flightDb.findByIdAndPilotLicenseNumber(id, licenseNumber);
	}
	@Override
	public List<FlightModel> getAllFlights(){
		return flightDb.findAll();
	}
}
