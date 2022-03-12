package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the form database table.
 * 
 */
@Entity
@NamedQuery(name="Form.findAll", query="SELECT f FROM Form f")
public class Form implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="form_id")
	private int formId;

	@Column(name="choronic_diseases")
	private String choronicDiseases;

	@Column(name="emergancy_level")
	private String emergancyLevel;

	//bi-directional many-to-one association to FormHospital
	@OneToMany(mappedBy="form")
	private List<FormHospital> formHospitals;

	//bi-directional one-to-one association to Patient
	@OneToOne(mappedBy="form")
	private Patient patient;

	public Form() {
	}

	public int getFormId() {
		return this.formId;
	}

	public void setFormId(int formId) {
		this.formId = formId;
	}

	public String getChoronicDiseases() {
		return this.choronicDiseases;
	}

	public void setChoronicDiseases(String choronicDiseases) {
		this.choronicDiseases = choronicDiseases;
	}

	public String getEmergancyLevel() {
		return this.emergancyLevel;
	}

	public void setEmergancyLevel(String emergancyLevel) {
		this.emergancyLevel = emergancyLevel;
	}

	public List<FormHospital> getFormHospitals() {
		return this.formHospitals;
	}

	public void setFormHospitals(List<FormHospital> formHospitals) {
		this.formHospitals = formHospitals;
	}

	public FormHospital addFormHospital(FormHospital formHospital) {
		getFormHospitals().add(formHospital);
		formHospital.setForm(this);

		return formHospital;
	}

	public FormHospital removeFormHospital(FormHospital formHospital) {
		getFormHospitals().remove(formHospital);
		formHospital.setForm(null);

		return formHospital;
	}

	public Patient getPatient() {
		return this.patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

}