package com.example.quarantinefinder.rest;

import com.example.quarantinefinder.constant.AbstractRestControllerImpl;
import com.example.quarantinefinder.criteria.FormCriteria;
import com.example.quarantinefinder.entity.Form;
import com.example.quarantinefinder.request.FormRequest;
import com.example.quarantinefinder.response.FormResponse;
import com.example.quarantinefinder.service.FormService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/form")
public class FormRestController extends AbstractRestControllerImpl<FormRequest, FormResponse, FormCriteria, Form, Long> {

    @Autowired
    @Getter
    private FormService service;
}
