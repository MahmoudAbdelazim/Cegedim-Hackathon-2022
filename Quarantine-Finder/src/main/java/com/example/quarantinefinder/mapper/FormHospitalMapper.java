package com.example.quarantinefinder.mapper;

import com.example.quarantinefinder.constant.AbstractMapper;
import com.example.quarantinefinder.entity.FormHospital;
import com.example.quarantinefinder.entity.Hospital;
import com.example.quarantinefinder.request.FormHospitalRequest;
import com.example.quarantinefinder.request.HospitalRequest;
import com.example.quarantinefinder.response.FormHospitalResponse;
import com.example.quarantinefinder.response.HospitalResponse;
import org.springframework.stereotype.Component;

@Component
public class FormHospitalMapper implements AbstractMapper<FormHospital, FormHospitalRequest, FormHospitalResponse> {
    @Override
    public FormHospital toEntity(FormHospitalRequest request) {
        FormHospital formHospital = new FormHospital();
        formHospital.setAcceptance(request.getAcceptance());
        return formHospital;
    }

    @Override
    public void toEntity(FormHospitalRequest request, FormHospital entity) {

    }

    @Override
    public FormHospitalResponse toBasicResponse(FormHospital entity) {
        FormHospitalResponse formHospitalResponse = new FormHospitalResponse();
        formHospitalResponse.setAcceptance(entity.getAcceptance());
        return formHospitalResponse;
    }
}