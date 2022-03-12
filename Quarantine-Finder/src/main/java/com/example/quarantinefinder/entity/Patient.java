package com.example.quarantinefinder.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity(name = "patient")
@Getter
@Setter
@RequiredArgsConstructor
@NoArgsConstructor
@Builder
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long patient_id;

    private Long national_id;
    private String firstName;
    private String lastName;
    private String address;

    @ManyToMany
    @JoinTable(
            name = "patient_hospital",
            joinColumns = @JoinColumn(name = "patient_id"),
            inverseJoinColumns = @JoinColumn(name = "hospital_id")
    )
    private List<Hospital> hospitals;

    @OneToOne(mappedBy = "patient")
    private Form form;
}
