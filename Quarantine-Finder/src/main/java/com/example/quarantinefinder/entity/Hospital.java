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
public class Hospital extends AbstractNamedEntity{
    private static final long serialVersionUID = 1L;

    private String address;

    private String name;

    @Column(name = "empty_beds")
    private Integer emptyBeds;

    private List<Patient> patients;

    private String city;

    //bi-directional many-to-many association to Patient
    @ManyToMany(mappedBy="hospitals")
    public List<Patient> getPatients() {
        return patients;
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