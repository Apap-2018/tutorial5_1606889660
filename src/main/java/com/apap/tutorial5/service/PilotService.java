package com.apap.tutorial5.service;

import com.apap.tutorial5.model.PilotModel;

public interface PilotService {
	PilotModel getPilotDetailByLicenseNumber(String licenseNumber);
	
	void addPilot(PilotModel pilot);
	
	void removePilotById(Long id);
	
	void updatePilot(PilotModel newPilot, String licenseNumber);

	PilotModel getPilotDetailById(Long pilotId);
}
