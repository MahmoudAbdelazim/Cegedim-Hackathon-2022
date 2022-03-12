package com.example.quarantinefinder.constant;

import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;



@Transactional
public abstract class AbstractServiceImpl<REQ, RES, E, ID> implements AbstractService<REQ, RES, E, ID> {

	protected Logger logger = LoggerFactory.getLogger(this.getClass());

	protected abstract AbstractMapper<E, REQ, RES> getMapper();

	protected abstract AbstractRepository<E, ID> getRepository();

	protected abstract AbstractValidator<E, ID> getValidator();


	@Override
	public List<RES> findAll(ResponseLevel level) {
		List<E> list = (List<E>) getRepository().findAll();
		List<RES> responses = getMapper().toResponses(list, level);
		responses = enrichResponse(responses, null, level);
		return responses;
	}

	@Override
	public RES findById(ID id, ResponseLevel level) {
		E entity = getValidator().validateExistence(id);
		RES response = getMapper().toResponse(entity, level);
		return enrichResponse(response, null, level);
	}

	@Override
	public List<RES> search(Specification<E> criteria, ResponseLevel responseLevel) {

		var entitys = getRepository().findAll(criteria);
		List<RES> responses = getMapper().toResponses(entitys, responseLevel);
		responses = enrichResponse(responses, criteria, responseLevel);
		return responses;
	}

	@Override
	public Page<RES> search(Specification<E> criteria, ResponseLevel responseLevel, Pageable pageable) {

		Page<E> page = getRepository().findAll(criteria, pageable);
		List<RES> responses = getMapper().toResponses(page.getContent(), responseLevel);
		responses = enrichResponse(responses, criteria, responseLevel);
		return new PageImpl<>(responses, pageable, page.getTotalElements());
	}


	@Override
	public void deleteById(ID id) {
		E entity = getValidator().validateExistence(id);
		validateDeletable(entity);
		getRepository().deleteById(id);
		postDelete(entity);
	}

	@Override
	public RES update(ID id, REQ request) {

		E entity = updateInTransaction(id, request);
		return getMapper().toResponse(entity);
	}

	public E updateInTransaction(ID id, REQ request) {
		validateUpdateRequest(id, request);

		E entity = getValidator().validateExistence(id);

		getMapper().toEntity(request, entity);
		preUpdate(request, entity);
		entity = getRepository().save(entity);
		postUpdate(request, entity);
		return entity;
	}

	public E updateInTransaction(ID id, REQ request, E entity) {
		validateUpdateRequest(id, request);
		preUpdate(request, entity);
		entity = getRepository().save(entity);
		postUpdate(request, entity);
		return entity;
	}

	public E createInMemoryEntity(REQ request) {
		logger.info("create using payload {}", request);
		validateCreateRequest(request);

		E entity = getMapper().toEntity(request);

		return entity;
	}

	public E createInTransaction(REQ request) {

		E entity = createInMemoryEntity(request);

		preCreate(request, entity);

		entity = getRepository().save(entity);
		postCreate(request, entity);
		return entity;
	}

	public E createInTransaction(REQ request, E entity) {
		logger.info("create using entity {}", entity);
		validateCreateRequest(request);

		preCreate(request, entity);

		entity = getRepository().save(entity);
		postCreate(request, entity);
		return entity;
	}

	@Override
	public RES create(REQ request) {

		E entity = createInTransaction(request);

		var response = getMapper().toResponse(entity);
		logger.info(" {} created", response);

		return response;
	}

	protected void validateUpdateRequest(ID id, REQ request) {
	}

	protected void preCreate(REQ request, E entity) {
	}

	protected void postCreate(REQ request, E entity) {
	}

	protected void preUpdate(REQ request, E entity) {

		// Always update differs than create at least in setting the ids
		// preCreate(request, entity);
	}

	protected void postUpdate(REQ request, E entity) {
	}

	protected void validateCreateRequest(REQ request) {
	}

	protected void validateDeletable(E entity) {
	}

	protected void postDelete(E entity) {

	}

	protected List<RES> enrichResponse(List<RES> responses, Specification<E> criteria, ResponseLevel responseLevel) {
		responses.parallelStream().forEach((response) -> {
			enrichResponse(response, criteria, responseLevel);
		});
		return responses;
	}

	protected RES enrichResponse(RES response, Specification<E> criteria, ResponseLevel responseLevel) {
		return response;
	}



}
