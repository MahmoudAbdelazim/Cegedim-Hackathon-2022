package com.example.quarantinefinder.mapper;

import org.springframework.beans.factory.annotation.Autowired;
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
        patient.setName(request.getName());
        patient.setLastName(request.getLastName());
        patient.setNationalId(request.getNationalId());
        patient.setAddress(request.getAddress());
        patient.setChoronicDiseases(request.getChoronicDiseases());
        patient.setEmergencyLevel(request.getEmergencyLevel());
        patient.setEmail(request.getEmail());
        patient.setPassword(request.getPassword());
        return patient;
    }

    @Override
    public void toEntity(PatientRequest request, Patient entity) {

    }

    @Override
    public PatientResponse toBasicResponse(Patient entity) {
        PatientResponse patientResponse = new PatientResponse();
        patientResponse.setAddress(entity.getAddress());
        patientResponse.setFirstName(entity.getName());
        patientResponse.setLastName(entity.getLastName());
        patientResponse.setNationalId(entity.getNationalId());
        patientResponse.setChoronicDiseases(entity.getChoronicDiseases());
        patientResponse.setEmergencyLevel(entity.getEmergencyLevel());
        patientResponse.setEmail(entity.getEmail());
        return patientResponse;
    }

}
