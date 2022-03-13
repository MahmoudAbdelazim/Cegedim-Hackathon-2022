package com.example.quarantinefinder.request;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigInteger;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class PatientRequest {
    private String name;
    private String lastName;
    private String address;
    private BigInteger nationalId;
    private FormRequest formRequest;
}
