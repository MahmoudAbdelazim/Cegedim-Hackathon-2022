package com.example.quarantinefinder.entity;

import javax.persistence.*;
import java.util.List;
import lombok.*;

@Entity
@Table(name = "form")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@NamedQuery(name="Form.findAll", query="SELECT f FROM Form f")
public class Form extends AbstractEntity{
    private static final long serialVersionUID = 1L;

    @Column(name="choronic_diseases")
    private String choronicDiseases;

    @Column(name="emergancy_level")
    private String emergancyLevel;

    private List<FormHospital> formHospitals;

    //bi-directional many-to-one association to FormHospital
    @OneToMany(mappedBy="form")
    public List<FormHospital> getFormHospitals() {
        return formHospitals;
    }

    private Patient patient;

    //bi-directional one-to-one association to Patient
    @OneToOne(mappedBy="form")
    public Patient getPatient() {
        return patient;
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

    @Id
    @Access(AccessType.PROPERTY)
    @Override
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "form_id", unique = true, nullable = false)
    public long getId() {
        return id;
    }

    @Override
    public void setId(long id) {
        this.id = id;
    }

}