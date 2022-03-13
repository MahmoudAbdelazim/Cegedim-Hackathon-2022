package com.example.quarantinefinder.response;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class FormResponse {
    private String choronicDiseases;
    private String emergencyLevel;
}
