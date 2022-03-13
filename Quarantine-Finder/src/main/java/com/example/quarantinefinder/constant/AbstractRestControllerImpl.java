package com.example.quarantinefinder.constant;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

public abstract class AbstractRestControllerImpl<REQ, RES, C extends SpecificationBuilder<E>, E, ID>
		implements AbstractController<REQ, RES, C, E, ID> {

	protected Logger logger = LoggerFactory.getLogger(this.getClass());

	protected abstract AbstractService<REQ, RES, E, ID> getService();

	@Override
	@CrossOrigin
	@GetMapping("/{response-level}")
	public List<RES> findAll(@PathVariable(name = "response-level", required = true) ResponseLevel level) {
		return getService().findAll(level);
	}

	@Override
	@CrossOrigin
	@PostMapping
	public RES create(@RequestBody REQ request) {
		return getService().create(request);
	}

	@Override
	@CrossOrigin
	@PutMapping("/{id}")
	public RES update(@PathVariable("id") ID id, @RequestBody REQ resquest) {
		return getService().update(id, resquest);
	}

	@Override
	@CrossOrigin
	@DeleteMapping("/{id}")
	public void delete(@PathVariable("id") ID id) {

		try {
			getService().deleteById(id);
		} catch (Exception e) {
			throw Exceptions.resourceCanNotBeDeleted(getEntityName(), id);
		}
	}

	@Override
	@CrossOrigin
	@GetMapping("/{id}/{response-level}")
	public RES find(@PathVariable("id") ID id,
			@PathVariable(name = "response-level", required = true) ResponseLevel level) {

		return getService().findById(id, level);
	}

	@Override
	@CrossOrigin
	@PostMapping("/search/{response-level}")
	public List<RES> search(@RequestBody C criteria,
			@PathVariable(name = "response-level", required = true) ResponseLevel responseLevel) {
		return getService().search(criteria, responseLevel);
	}

	@Override
	@CrossOrigin
	@PostMapping("/search/{response-level}/page")
	public Page<RES> search(@RequestBody C criteria,
			@PathVariable(name = "response-level", required = true) ResponseLevel responseLevel, Pageable pageable) {
		return getService().search(criteria, responseLevel, pageable);
	}

	@SuppressWarnings("unchecked")
	protected String getEntityName() {

		String fullEntityClassName = ((Class<E>) ((ParameterizedType) getClass().getGenericSuperclass())
				.getActualTypeArguments()[3]).getTypeName();

		var names = fullEntityClassName.split("\\.");
		return names[names.length - 1];
	}

	@SuppressWarnings("unchecked")
	protected Class<E> getEntityClass() {
		return (Class<E>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[3];
	}

}
