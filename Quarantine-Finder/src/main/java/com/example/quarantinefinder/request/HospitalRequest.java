package com.example.quarantinefinder.request;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class HospitalRequest {
    private String name;
    private String address;
    private Integer emptyBeds;
    private String city;
}
