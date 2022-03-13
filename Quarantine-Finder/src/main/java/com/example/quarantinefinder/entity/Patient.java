package com.example.quarantinefinder.entity;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "Patient")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Patient extends AbstractNamedEntity {

	@Column(name = "national_id")
	private Long nationalId;
	@Column(name = "last_name")
	private String lastName;
	private String address;

	/*
	 * //bi-directional one-to-one association to Form
	 * 
	 * @OneToOne
	 * 
	 * @JoinColumn(name="patient_id") private Form form;
	 * 
	 * //bi-directional many-to-many association to Hospital
	 * 
	 * @ManyToMany
	 * 
	 * @JoinTable( name="patient_hospital" , joinColumns={
	 * 
	 * @JoinColumn(name="patient_id") } , inverseJoinColumns={
	 * 
	 * @JoinColumn(name="hospital_id") } )
	 */
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
	@Column(name = "first_name", nullable = false)
	@Access(AccessType.PROPERTY)
	public String getName() {
		return name;
	}

}
