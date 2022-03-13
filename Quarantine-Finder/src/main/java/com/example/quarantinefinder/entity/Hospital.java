package com.example.quarantinefinder.entity;

import javax.persistence.*;
import java.util.List;
import lombok.*;

@Entity
@Table(name="hospital")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@NamedQuery(name="Hospital.findAll", query="SELECT h FROM Hospital h")
public class Hospital extends AbstractNamedEntity{
    private static final long serialVersionUID = 1L;

    private String address;

    private String name;

    private List<FormHospital> formHospitals;

    private List<Patient> patients;

    //bi-directional many-to-one association to FormHospital
    @OneToMany(mappedBy="hospital")
    public List<FormHospital> getFormHospitals() {
        return formHospitals;
    }

    //bi-directional many-to-many association to Patient
    @ManyToMany(mappedBy="hospitals")
    public List<Patient> getPatients() {
        return patients;
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

    @Id
    @Access(AccessType.PROPERTY)
    @Override
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "hospital_id", unique = true, nullable = false)
    public long getId() {
        return id;
    }

    @Override
    public void setId(long id) {
        this.id = id;
    }

    @Override
    @Column(name = "name", nullable = false, length = 50)
    @Access(AccessType.PROPERTY)
    public String getName() {
        return name;
    }
}
