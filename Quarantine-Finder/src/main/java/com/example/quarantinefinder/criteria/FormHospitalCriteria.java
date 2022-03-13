package com.example.quarantinefinder.criteria;

import com.example.quarantinefinder.constant.SpecificationBuilder;
import com.example.quarantinefinder.entity.FormHospital;
import com.example.quarantinefinder.entity.Hospital;
import com.example.quarantinefinder.parameters.FormHospitalSearchParameters;
import com.example.quarantinefinder.parameters.HospitalSearchParameters;

public class FormHospitalCriteria extends SpecificationBuilder<FormHospital> {
    private FormHospitalSearchParameters parameters;
    @Override
    protected void fillExpressions() {
        of("id").is(parameters.getId());
    }
}