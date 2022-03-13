package com.example.quarantinefinder.validatore;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.quarantinefinder.constant.AbstractNamedEntityValidator;
import com.example.quarantinefinder.entity.Patient;
import com.example.quarantinefinder.repo.PatientRepo;

import lombok.Getter;
@Component
public class PatientValidator extends AbstractNamedEntityValidator<Patient> {
	@Autowired
	@Getter
	private PatientRepo repository;
}
