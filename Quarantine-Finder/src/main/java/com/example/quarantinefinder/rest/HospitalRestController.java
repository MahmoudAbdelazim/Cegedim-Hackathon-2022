package com.example.quarantinefinder.rest;

import com.example.quarantinefinder.constant.AbstractRestControllerImpl;
import com.example.quarantinefinder.criteria.HospitalCriteria;
import com.example.quarantinefinder.entity.Hospital;
import com.example.quarantinefinder.request.HospitalRequest;
import com.example.quarantinefinder.response.HospitalResponse;
import com.example.quarantinefinder.service.HospitalService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hospital")
public class HospitalRestController extends AbstractRestControllerImpl<HospitalRequest, HospitalResponse, HospitalCriteria, Hospital, Long> {

    @Autowired
    @Getter
    private HospitalService service;
}
