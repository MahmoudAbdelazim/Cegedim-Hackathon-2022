package com.example.quarantinefinder.request;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class PatientRequest {
    private String fName;
    private String lName;
    private String address;
}
