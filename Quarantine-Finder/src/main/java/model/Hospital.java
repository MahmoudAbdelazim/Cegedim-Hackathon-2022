package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the hospital database table.
 * 
 */
@Entity
@NamedQuery(name="Hospital.findAll", query="SELECT h FROM Hospital h")
public class Hospital implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="hospital_id")
	private int hospitalId;

	private String address;

	private String name;

	//bi-directional many-to-one association to FormHospital
	@OneToMany(mappedBy="hospital")
	private List<FormHospital> formHospitals;

	//bi-directional many-to-many association to Patient
	@ManyToMany(mappedBy="hospitals")
	private List<Patient> patients;

	public Hospital() {
	}

	public int getHospitalId() {
		return this.hospitalId;
	}

	public void setHospitalId(int hospitalId) {
		this.hospitalId = hospitalId;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<FormHospital> getFormHospitals() {
		return this.formHospitals;
	}

	public void setFormHospitals(List<FormHospital> formHospitals) {
		this.formHospitals = formHospitals;
	}

	public FormHospital addFormHospital(FormHospital formHospital) {
		getFormHospitals().add(formHospital);
		formHospital.setHospital(this);

		return formHospital;
	}

	public FormHospital removeFormHospital(FormHospital formHospital) {
		getFormHospitals().remove(formHospital);
		formHospital.setHospital(null);

		return formHospital;
	}

	public List<Patient> getPatients() {
		return this.patients;
	}

	public void setPatients(List<Patient> patients) {
		this.patients = patients;
	}

}