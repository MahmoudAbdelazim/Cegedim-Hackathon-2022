package com.example.quarantinefinder.repo;

import com.example.quarantinefinder.constant.AbstractRepository;
import com.example.quarantinefinder.entity.FormHospital;
import com.example.quarantinefinder.entity.Patient;
import org.springframework.stereotype.Repository;

@Repository
public interface FormHospitalRepo extends AbstractRepository<FormHospital, Long> {
}
