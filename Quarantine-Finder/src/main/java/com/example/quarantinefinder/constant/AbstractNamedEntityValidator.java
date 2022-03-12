package com.example.quarantinefinder.constant;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.example.quarantinefinder.entity.AbstractNamedEntity;

public abstract class AbstractNamedEntityValidator<E extends AbstractNamedEntity> extends AbstractValidator<E, Long> {



	@PersistenceContext
	private EntityManager entityManager;

	private Class<E> entityClass;

	private String nameIsUnique;

	@PostConstruct
	private void init() {

		String entityName = getEntityName();
		entityClass = getEntityClass();
		String jpql = "SELECT e FROM " + entityName + " e where LOWER(e.name) = LOWER(?1)";

		TypedQuery<E> notIsUniqueQuerty = entityManager.createQuery(jpql, entityClass);
		nameIsUnique = entityName + ".validate.name.is.unique";
		entityManager.getEntityManagerFactory().addNamedQuery(nameIsUnique, notIsUniqueQuerty);

	}

	public void validateNameIsUnique(String name) {

		var matching = entityManager.createNamedQuery(nameIsUnique, entityClass).setParameter(1, name).getResultList();
		if (!matching.isEmpty()) {
			throw Exceptions.resourceFound("{} '{}' already exist","name",name, getEntityName(), name);
		}
	}

	
}
