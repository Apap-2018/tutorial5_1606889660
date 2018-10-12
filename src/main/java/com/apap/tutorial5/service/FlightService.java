package com.apap.tutorial5.service;

import java.util.List;

import com.apap.tutorial5.model.FlightModel;

public interface FlightService {
	void addFlight(FlightModel flight);
	List<FlightModel> getFlightDetailByLicenseNumber(String licenseNumber);
	void removeById(Long id);
	void update(FlightModel newFlight);
	FlightModel getFlightDetailByIdAndPilotLicenseNumber(long id, String licenseNumber);
	List<FlightModel> getAllFlights();
}
