package com.example.quarantinefinder.mapper;

import com.example.quarantinefinder.entity.Form;
import com.example.quarantinefinder.request.FormRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.quarantinefinder.constant.AbstractMapper;
import com.example.quarantinefinder.entity.Patient;
import com.example.quarantinefinder.request.PatientRequest;
import com.example.quarantinefinder.response.PatientResponse;


@Component
public class PaitentMapper implements AbstractMapper<Patient, PatientRequest, PatientResponse> {

    @Autowired
    private FormMapper formMapper;

    @Override
    public Patient toEntity(PatientRequest request) {
        Patient patient = new Patient();
        Form form = formMapper.toEntity(request.getFormRequest());
        patient.setForm(form);
        patient.setName(request.getName());
        patient.setLastName(request.getLastName());
        patient.setNationalId(request.getNationalId());
        patient.setAddress(request.getAddress());
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
        patientResponse.setFormResponse(formMapper.toBasicResponse(entity.getForm()));
        return patientResponse;
    }

}
