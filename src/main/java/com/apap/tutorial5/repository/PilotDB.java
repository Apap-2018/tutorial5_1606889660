package com.apap.tutorial5.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.apap.tutorial5.model.PilotModel;

@Repository
public interface PilotDB extends JpaRepository<PilotModel, Long>{
	PilotModel findByLicenseNumber(String licenseNumber);
	void deleteById(Long id);
	Optional<PilotModel> findById(Long id);
}
