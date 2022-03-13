package com.example.quarantinefinder.request;

import com.example.quarantinefinder.entity.Patient;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class FormRequest {
    private String choronicDiseases;
    private String emergencyLevel;
}
