package com.example.quarantinefinder.criteria;

import com.example.quarantinefinder.constant.SpecificationBuilder;
import com.example.quarantinefinder.entity.Hospital;
import com.example.quarantinefinder.parameters.HospitalSearchParameters;

public class HospitalCriteria extends SpecificationBuilder<Hospital> {
    private HospitalSearchParameters parameters;
    @Override
    protected void fillExpressions() {
        of("id").is(parameters.getId());
    }
}
