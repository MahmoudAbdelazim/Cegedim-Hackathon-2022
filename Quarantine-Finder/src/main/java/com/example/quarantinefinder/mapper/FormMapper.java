package com.example.quarantinefinder.mapper;

import com.example.quarantinefinder.constant.AbstractMapper;
import com.example.quarantinefinder.entity.Form;
import com.example.quarantinefinder.entity.Patient;
import com.example.quarantinefinder.repo.PatientRepo;
import com.example.quarantinefinder.request.FormRequest;
import com.example.quarantinefinder.request.PatientRequest;
import com.example.quarantinefinder.response.FormResponse;
import com.example.quarantinefinder.response.PatientResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FormMapper implements AbstractMapper<Form, FormRequest, FormResponse> {

    @Override
    public Form toEntity(FormRequest request) {
        Form form = new Form();
        form.setChoronicDiseases(request.getChoronicDiseases());
        form.setEmergancyLevel(request.getEmergencyLevel());
        return form;
    }

    @Override
    public void toEntity(FormRequest request, Form entity) {

    }

    @Override
    public FormResponse toBasicResponse(Form entity) {
        FormResponse formResponse = new FormResponse();
        formResponse.setEmergencyLevel(entity.getEmergancyLevel());
        formResponse.setChoronicDiseases(entity.getChoronicDiseases());
        return formResponse;
    }
}
