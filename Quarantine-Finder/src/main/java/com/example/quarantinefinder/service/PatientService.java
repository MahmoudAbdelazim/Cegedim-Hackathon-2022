package com.example.quarantinefinder.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.quarantinefinder.constant.AbstractServiceImpl;
import com.example.quarantinefinder.entity.Patient;
import com.example.quarantinefinder.mapper.PaitentMapper;
import com.example.quarantinefinder.repo.PatientRepo;
import com.example.quarantinefinder.request.PatientRequest;
import com.example.quarantinefinder.response.PatientResponse;
import com.example.quarantinefinder.validatore.PatientValidator;

import lombok.Getter;
@Service
public class PatientService extends AbstractServiceImpl<PatientRequest, PatientResponse, Patient, Long> {
	@Autowired
	@Getter
	private PaitentMapper mapper;

	@Autowired
	@Getter
	private PatientValidator validator;

	@Autowired
	@Getter
	private PatientRepo repository;
}
