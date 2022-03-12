package com.example.quarantinefinder.repo;

import org.springframework.stereotype.Repository;

import com.example.quarantinefinder.constant.AbstractRepository;
import com.example.quarantinefinder.entity.Hospital;

@Repository
public interface HospitalRepo extends AbstractRepository<Hospital, Long> {
}
