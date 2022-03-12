package com.example.quarantinefinder.entity;

import javax.persistence.*;
import java.util.List;
import lombok.*;

@Entity(name = "hospital")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Hospital extends AbstractNamedEntity{

   
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

    private String address;

    @ManyToMany(mappedBy = "hospitals")
    private List<Patient> patients;

    @OneToMany(mappedBy = "hospitals")
    private List<Form> forms;
}
