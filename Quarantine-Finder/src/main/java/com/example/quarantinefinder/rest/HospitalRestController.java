package com.example.quarantinefinder.rest;

import com.example.quarantinefinder.constant.AbstractRestControllerImpl;
import com.example.quarantinefinder.criteria.HospitalCriteria;
import com.example.quarantinefinder.entity.Hospital;
import com.example.quarantinefinder.entity.Patient;
import com.example.quarantinefinder.repo.HospitalRepo;
import com.example.quarantinefinder.repo.PatientRepo;
import com.example.quarantinefinder.request.HospitalRequest;
import com.example.quarantinefinder.request.PatientRequest;
import com.example.quarantinefinder.response.HospitalResponse;
import com.example.quarantinefinder.service.HospitalService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hospital")
public class HospitalRestController extends AbstractRestControllerImpl<HospitalRequest, HospitalResponse, HospitalCriteria, Hospital, Long> {

    @Autowired
    @Getter
    private HospitalService service;

    @Autowired
    private HospitalRepo hospitalRepo;

    @Autowired
    private PatientRepo patientRepo;

    @GetMapping("/{city}")
    public Iterable<Hospital> getHospitalsInCity(@PathVariable String city) {
        return hospitalRepo.getAllByCity(city);
    }

    @PostMapping("/apply/{id}")
    public boolean apply(@PathVariable Long id, @RequestBody PatientRequest patientRequest) {
        if (patientRepo.getByEmail(patientRequest.getEmail()).isPresent()) {
            if (hospitalRepo.findById(id).isPresent()) {
                Patient patient = patientRepo.getByEmail(patientRequest.getEmail()).get();
                Hospital hospital = hospitalRepo.findById(id).get();
                if (hospital.getEmptyBeds() > 0) {
                    patient.setChoronicDiseases(patientRequest.getChoronicDiseases());
                    patient.setEmergencyLevel(patientRequest.getEmergencyLevel());
                    hospital.setEmptyBeds(hospital.getEmptyBeds() - 1);
                    hospitalRepo.save(hospital);
                    patientRepo.save(patient);
                    return true;
                }
            }
        }
        return false;
    }
}
