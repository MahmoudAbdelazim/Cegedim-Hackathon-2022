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
public class Hospital {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long hospital_id;

    private String name;
    private String address;

    @ManyToMany(mappedBy = "hospitals")
    private List<Patient> patients;

    @OneToMany(mappedBy = "hospitals")
    private List<Form> forms;
}
