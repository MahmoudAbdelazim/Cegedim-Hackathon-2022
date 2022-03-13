package com.example.quarantinefinder.criteria;

import com.example.quarantinefinder.constant.SpecificationBuilder;
import com.example.quarantinefinder.entity.Patient;
import com.example.quarantinefinder.parameters.PatientSearchParameters;

public class PatientCriteria extends SpecificationBuilder<Patient> {

    private PatientSearchParameters parameters;

    @Override
    protected void fillExpressions() {
        of("id").is(parameters.getId());
    }

}
