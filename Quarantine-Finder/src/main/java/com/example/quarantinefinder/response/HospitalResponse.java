package com.example.quarantinefinder.response;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class HospitalResponse {
    private String name;
    private String address;
    private Integer emptyBeds;
    private String city;
    private Long id;
}
