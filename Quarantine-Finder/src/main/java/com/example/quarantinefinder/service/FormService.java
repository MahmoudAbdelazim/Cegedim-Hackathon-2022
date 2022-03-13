package com.example.quarantinefinder.service;

import com.example.quarantinefinder.constant.AbstractMapper;
import com.example.quarantinefinder.constant.AbstractServiceImpl;
import com.example.quarantinefinder.constant.AbstractValidator;
import com.example.quarantinefinder.entity.Form;
import com.example.quarantinefinder.mapper.FormMapper;
import com.example.quarantinefinder.mapper.HospitalMapper;
import com.example.quarantinefinder.repo.FormRepo;
import com.example.quarantinefinder.request.FormRequest;
import com.example.quarantinefinder.response.FormResponse;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FormService extends AbstractServiceImpl<FormRequest, FormResponse, Form, Long> {

    @Autowired
    @Getter
    private FormRepo repository;

    @Autowired
    @Getter
    private FormMapper mapper;

    @Override
    protected AbstractValidator<Form, Long> getValidator() {
        return null;
    }
}
