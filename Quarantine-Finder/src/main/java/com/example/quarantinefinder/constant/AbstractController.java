package com.example.quarantinefinder.constant;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * 
 * @author Ahmed Khalil
 *
 * @param <REQ> request
 * @param <RES> response
 * @param <C>   criteria
 * @param <E>   entity
 * @param <ID>  id class
 */
public interface AbstractController<REQ, RES, C extends SpecificationBuilder<E>, E, ID> {

	public RES create(REQ dto);

	public RES update(ID id, REQ dto);

	public void delete(ID id);

	public RES find(ID id, ResponseLevel responseLevel);

	public List<RES> findAll(ResponseLevel level);

	public Page<RES> search(C criteria, ResponseLevel responseLevel, Pageable pageable);

	public List<RES> search(C criteria, ResponseLevel responseLevel);

	

}
