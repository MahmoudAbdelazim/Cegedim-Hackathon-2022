package com.example.quarantinefinder.entity;

import javax.persistence.*;
import java.util.List;
import lombok.*;

@Entity(name = "form")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Form extends AbstractEntity{

    

	/*
	 * @OneToOne
	 * 
	 * @JoinColumn(name = "patient_id") private Patient patient;
	 */

    @ManyToOne
    @JoinColumn(name = "hospital_id")
    private Hospital hospitals;

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
