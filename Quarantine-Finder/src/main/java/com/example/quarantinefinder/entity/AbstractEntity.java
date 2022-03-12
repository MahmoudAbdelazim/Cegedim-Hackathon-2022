package com.example.quarantinefinder.entity;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public abstract class AbstractEntity implements Serializable {

	protected long id;

	public abstract long getId();

	public void setId(long id) {
		this.id = id;
	}

}
