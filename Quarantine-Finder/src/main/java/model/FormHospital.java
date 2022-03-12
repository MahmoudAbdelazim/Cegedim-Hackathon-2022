package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the form_hospital database table.
 * 
 */
@Entity
@Table(name="form_hospital")
@NamedQuery(name="FormHospital.findAll", query="SELECT f FROM FormHospital f")
public class FormHospital implements Serializable {
	private static final long serialVersionUID = 1L;

	private byte acceptance;

	//bi-directional many-to-one association to Form
	@ManyToOne
	@JoinColumn(name="form_id")
	private Form form;

	//bi-directional many-to-one association to Hospital
	@ManyToOne
	@JoinColumn(name="hospital_id")
	private Hospital hospital;

	public FormHospital() {
	}

	public byte getAcceptance() {
		return this.acceptance;
	}

	public void setAcceptance(byte acceptance) {
		this.acceptance = acceptance;
	}

	public Form getForm() {
		return this.form;
	}

	public void setForm(Form form) {
		this.form = form;
	}

	public Hospital getHospital() {
		return this.hospital;
	}

	public void setHospital(Hospital hospital) {
		this.hospital = hospital;
	}

}