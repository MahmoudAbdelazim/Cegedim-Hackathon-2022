package com.example.quarantinefinder.repo;

import org.springframework.stereotype.Repository;

import com.example.quarantinefinder.constant.AbstractRepository;
import com.example.quarantinefinder.entity.Patient;

import java.util.Optional;

@Repository
public interface PatientRepo extends AbstractRepository<Patient, Long> {
    public Optional<Patient> getByEmail(String email);
}
