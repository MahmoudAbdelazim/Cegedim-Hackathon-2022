package com.example.quarantinefinder.rest;

import com.example.quarantinefinder.entity.Patient;
import com.example.quarantinefinder.repo.PatientRepo;
import com.example.quarantinefinder.request.PatientRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private PatientRepo patientRepo;

    @PostMapping
    public boolean login(@RequestBody PatientRequest patientRequest) {
        String email = patientRequest.getEmail();
        String password = patientRequest.getPassword();
        if (patientRepo.getByEmail(email).isPresent()) {
            Patient patient = patientRepo.getByEmail(email).get();
            return patient.getPassword().equals(password);
        }
        return false;
    }
}
