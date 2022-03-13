package com.example.quarantinefinder.entity;

import java.math.BigInteger;
import java.util.List;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "patient")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Patient extends AbstractNamedEntity {

	private static final long serialVersionUID = 1L;

	private String address;

	@Column(name = "last_name")
	private String lastName;

	@Column(name = "national_id")
	private BigInteger nationalId;

	private List<Hospital> hospitals;

	@Column(name = "choronic_diseases")
	private String choronicDiseases;

	@Column(name = "emergency_level")
	private String emergencyLevel;

	private String email;

	private String password;

	//bi-directional many-to-many association to Hospital
	@ManyToMany
	@JoinTable(
			name = "patient_hospital",
			joinColumns = @JoinColumn(name = "patient_id"),
			inverseJoinColumns = @JoinColumn(name = "hospital_id")
	)
	public List<Hospital> getHospitals() {
		return hospitals;
	}

	@Id
	@Access(AccessType.PROPERTY)
	@Override
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "patient_id", unique = true, nullable = false)
	public long getId() {
		return id;
	}

	@Override
	public void setId(long id) {
		this.id = id;
	}

	@Override
	@Column(name = "first_name", nullable = false, length = 50)
	@Access(AccessType.PROPERTY)
	public String getName() {
		return name;
	}
}