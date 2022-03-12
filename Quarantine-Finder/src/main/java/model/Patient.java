package model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigInteger;
import java.util.List;


/**
 * The persistent class for the patient database table.
 * 
 */
@Entity
@NamedQuery(name="Patient.findAll", query="SELECT p FROM Patient p")
public class Patient implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="patient_id")
	private int patientId;

	private String address;

	private String firstName;

	private String lastName;

	@Column(name="nantional_id")
	private BigInteger nantionalId;

	//bi-directional one-to-one association to Form
	@OneToOne
	@JoinColumn(name="patient_id")
	private Form form;

	//bi-directional many-to-many association to Hospital
	@ManyToMany
	@JoinTable(
		name="patient_hospital"
		, joinColumns={
			@JoinColumn(name="patient_id")
			}
		, inverseJoinColumns={
			@JoinColumn(name="hospital_id")
			}
		)
	private List<Hospital> hospitals;

	public Patient() {
	}

	public int getPatientId() {
		return this.patientId;
	}

	public void setPatientId(int patientId) {
		this.patientId = patientId;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public BigInteger getNantionalId() {
		return this.nantionalId;
	}

	public void setNantionalId(BigInteger nantionalId) {
		this.nantionalId = nantionalId;
	}

	public Form getForm() {
		return this.form;
	}

	public void setForm(Form form) {
		this.form = form;
	}

	public List<Hospital> getHospitals() {
		return this.hospitals;
	}

	public void setHospitals(List<Hospital> hospitals) {
		this.hospitals = hospitals;
	}

}