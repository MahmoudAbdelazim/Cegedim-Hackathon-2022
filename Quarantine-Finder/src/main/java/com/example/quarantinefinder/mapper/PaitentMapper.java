package com.example.quarantinefinder.mapper;

import org.springframework.stereotype.Component;

import com.example.quarantinefinder.constant.AbstractMapper;
import com.example.quarantinefinder.entity.Patient;
import com.example.quarantinefinder.request.PatientRequest;
import com.example.quarantinefinder.response.PatientResponse;
@Component
public class PaitentMapper implements AbstractMapper<Patient, PatientRequest, PatientResponse> {

	@Override
	public Patient toEntity(PatientRequest request) {
		Patient patient = new Patient();
		patient.setName(request.getFName());
		patient.setLastName(request.getLName());
		return patient;
	}

	@Override
	public void toEntity(PatientRequest request, Patient entity) {

	}

	@Override
	public PatientResponse toBasicResponse(Patient entity) {
		PatientResponse patientResponse = new PatientResponse();
		patientResponse.setAddress(entity.getAddress());
		patientResponse.setFName(entity.getName());
		patientResponse.setLName(entity.getLastName());
		return patientResponse;
	}

}
