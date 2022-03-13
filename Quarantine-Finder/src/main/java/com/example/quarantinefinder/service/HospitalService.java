package com.example.quarantinefinder.service;

import com.example.quarantinefinder.constant.AbstractMapper;
import com.example.quarantinefinder.constant.AbstractServiceImpl;
import com.example.quarantinefinder.constant.AbstractValidator;
import com.example.quarantinefinder.entity.Hospital;
import com.example.quarantinefinder.repo.HospitalRepo;
import com.example.quarantinefinder.request.HospitalRequest;
import com.example.quarantinefinder.response.HospitalResponse;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HospitalService extends AbstractServiceImpl<HospitalRequest, HospitalResponse, Hospital, Long> {

    @Autowired
    @Getter
    private HospitalRepo repository;

    @Override
    protected AbstractMapper<Hospital, HospitalRequest, HospitalResponse> getMapper() {
        return null;
    }

    @Override
    protected AbstractValidator<Hospital, Long> getValidator() {
        return null;
    }
}
