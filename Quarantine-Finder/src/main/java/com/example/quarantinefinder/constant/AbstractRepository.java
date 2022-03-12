package com.example.quarantinefinder.constant;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;

@NoRepositoryBean
public interface AbstractRepository<E, Id> extends PagingAndSortingRepository<E, Id>, JpaSpecificationExecutor<E> {

}
