package com.example.quarantinefinder.rest;

import com.example.quarantinefinder.constant.AbstractRestControllerImpl;
import com.example.quarantinefinder.criteria.FormHospitalCriteria;
import com.example.quarantinefinder.entity.FormHospital;
import com.example.quarantinefinder.request.FormHospitalRequest;
import com.example.quarantinefinder.response.FormHospitalResponse;
import com.example.quarantinefinder.service.FormHospitalService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/formHospital")
public class FormHospitalsRestController extends AbstractRestControllerImpl<FormHospitalRequest, FormHospitalResponse, FormHospitalCriteria, FormHospital, Long> {

    @Autowired
    @Getter
    private FormHospitalService service;
}
