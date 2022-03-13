package com.example.quarantinefinder.criteria;

import com.example.quarantinefinder.constant.SpecificationBuilder;
import com.example.quarantinefinder.entity.Form;
import com.example.quarantinefinder.parameters.FormSearchParameters;

public class FormCriteria extends SpecificationBuilder<Form> {
    private FormSearchParameters parameters;
    @Override
    protected void fillExpressions() {
        of("id").is(parameters.getId());
    }
}
