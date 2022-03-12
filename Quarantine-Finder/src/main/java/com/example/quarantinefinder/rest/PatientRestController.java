package com.example.quarantinefinder.rest;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.quarantinefinder.constant.AbstractRestControllerImpl;
import com.example.quarantinefinder.criteria.PatientCriteria;
import com.example.quarantinefinder.entity.Patient;
import com.example.quarantinefinder.request.PatientRequest;
import com.example.quarantinefinder.response.PatientResponse;
import com.example.quarantinefinder.service.PatientService;

import lombok.Getter;

public class PatientRestController extends AbstractRestControllerImpl<PatientRequest, PatientResponse, PatientCriteria, Patient, Long> {

	@Autowired
	@Getter
	private PatientService service;





}
