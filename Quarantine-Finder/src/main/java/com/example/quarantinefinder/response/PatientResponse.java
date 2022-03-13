package com.example.quarantinefinder.response;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigInteger;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class PatientResponse {
    private String firstName;
    private String lastName;
    private String address;
    private BigInteger nationalId;
    private String choronicDiseases;
    private String emergencyLevel;
    private String email;
}
