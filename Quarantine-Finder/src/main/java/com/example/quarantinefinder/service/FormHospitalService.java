package com.example.quarantinefinder.service;

import com.example.quarantinefinder.constant.AbstractMapper;
import com.example.quarantinefinder.constant.AbstractServiceImpl;
import com.example.quarantinefinder.constant.AbstractValidator;
import com.example.quarantinefinder.entity.FormHospital;
import com.example.quarantinefinder.mapper.FormHospitalMapper;
import com.example.quarantinefinder.mapper.HospitalMapper;
import com.example.quarantinefinder.repo.FormHospitalRepo;
import com.example.quarantinefinder.request.FormHospitalRequest;
import com.example.quarantinefinder.response.FormHospitalResponse;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FormHospitalService extends AbstractServiceImpl<FormHospitalRequest, FormHospitalResponse, FormHospital, Long> {

    @Autowired
    @Getter
    private FormHospitalRepo repository;

    @Autowired
    @Getter
    private FormHospitalMapper mapper;

    @Override
    protected AbstractValidator<FormHospital, Long> getValidator() {
        return null;
    }
}