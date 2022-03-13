package com.example.quarantinefinder.response;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class PatientResponse {
	 private String fName;
	 private String lName;
	 private String address;
}
