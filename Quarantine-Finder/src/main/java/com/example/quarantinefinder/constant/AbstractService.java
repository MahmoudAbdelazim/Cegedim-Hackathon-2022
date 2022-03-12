package com.example.quarantinefinder.constant;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

/**
 * 
 * @author Ahmed Khalil
 *
 * @param <REQ> request
 * @param <RES> response
 * @param <E>   entity
 * @param <ID>  id
 */
public interface AbstractService<REQ, RES, E, ID> {

	public RES findById(ID id, ResponseLevel level);

	public RES create(REQ dto);

	public RES update(ID id, REQ dto);

	public void deleteById(ID id);

	public List<RES> findAll(ResponseLevel level);

	public Page<RES> search(Specification<E> criteria, ResponseLevel responseLevel, Pageable pageable);

	public List<RES> search(Specification<E> criteria, ResponseLevel responseLevel);



}
