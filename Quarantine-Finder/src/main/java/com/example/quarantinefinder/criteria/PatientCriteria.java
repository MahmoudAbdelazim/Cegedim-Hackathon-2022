package com.example.quarantinefinder.criteria;

import com.attractik.pharmaclinic.core.dto.parameters.CountrySearchParameters;
import com.attractik.pharmaclinic.core.entity.Country;
import com.attractik.pharmaclinic.framework.criteria.SpecificationBuilder;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class PatientCriteria extends SpecificationBuilder<> {

	
		private  CountrySearchParameters parameters;

		@Override
		protected void fillExpressions() {

			of("id").is(parameters.getId());

			of("name").like(parameters.getName());
			
			of("currency").like(parameters.getCurrency());

			of("shortName").like(parameters.getShortName());

			of("migrationId").is(parameters.getMigrationId());

		}

	

}
