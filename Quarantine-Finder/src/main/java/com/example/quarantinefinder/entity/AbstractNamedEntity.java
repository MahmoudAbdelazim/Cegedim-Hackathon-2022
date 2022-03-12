package com.example.quarantinefinder.entity;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public abstract class AbstractNamedEntity extends AbstractEntity {

	protected String name;

	public abstract String getName();

	public void setName(String name) {
		this.name = name;
	}

}
