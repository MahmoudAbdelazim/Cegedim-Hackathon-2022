package com.example.quarantinefinder.entity;

import lombok.*;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the form_hospital database table.
 * 
 */
@Entity
@Table(name="form_hospital")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@NamedQuery(name="FormHospital.findAll", query="SELECT f FROM FormHospital f")
public class FormHospital extends AbstractEntity {
	private static final long serialVersionUID = 1L;

	private byte acceptance;

	private Form form;

	//bi-directional many-to-one association to Form
	@ManyToOne
	@JoinColumn(name="form_id")
	public Form getForm() {
		return form;
	}

	private Hospital hospital;

	//bi-directional many-to-one association to Hospital
	@ManyToOne
	@JoinColumn(name="hospital_id")
	public Hospital getHospital() {
		return hospital;
	}

	@Id
	@Access(AccessType.PROPERTY)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "form_hospital_id", unique = true, nullable = false)
	public long getId() {
		return id;
	}

	@Override
	public void setId(long id) {
		this.id = id;
	}
}