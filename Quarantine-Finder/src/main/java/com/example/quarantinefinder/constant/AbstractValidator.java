package com.example.quarantinefinder.constant;

import java.lang.reflect.ParameterizedType;

public abstract class AbstractValidator<E, ID> {

	public abstract AbstractRepository<E, ID> getRepository();

	public E validateExistence(ID id) {
	
		if (id == null)
			throw Exceptions.notFound("{} '{}' not found", getEntityName(), id);
		return getRepository().findById(id).orElseThrow(() -> Exceptions.notFound("{} '{}' not found", getEntityName(), id, getEntityName(), id));
	}

	@SuppressWarnings("unchecked")
	protected String getEntityName() {

		String fullEntityClassName = ((Class<E>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0]).getTypeName();

		var names = fullEntityClassName.split("\\.");
		return names[names.length - 1];
	}

	@SuppressWarnings("unchecked")
	protected Class<E> getEntityClass() {
		return (Class<E>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}

}
