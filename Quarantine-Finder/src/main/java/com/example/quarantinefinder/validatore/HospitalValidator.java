package com.example.quarantinefinder.validatore;

import com.example.quarantinefinder.entity.Hospital;
import com.example.quarantinefinder.repo.HospitalRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.quarantinefinder.constant.AbstractNamedEntityValidator;
import com.example.quarantinefinder.entity.Patient;
import com.example.quarantinefinder.repo.PatientRepo;

import lombok.Getter;
@Component
public class HospitalValidator extends AbstractNamedEntityValidator<Hospital> {
    @Autowired
    @Getter
    private HospitalRepo repository;
}
