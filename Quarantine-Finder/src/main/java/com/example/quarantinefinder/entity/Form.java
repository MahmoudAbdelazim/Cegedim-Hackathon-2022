/*
 * package com.example.quarantinefinder.entity;
 * 
 * import javax.persistence.*; import java.util.List; import lombok.*; import
 * model.FormHospital; import model.Patient;
 * 
 * @Entity(name = "form")
 * 
 * @Getter
 * 
 * @Setter
 * 
 * @AllArgsConstructor
 * 
 * @NoArgsConstructor
 * 
 * @Builder public class Form extends AbstractEntity{
 * 
 * 
 * 
 * 
 * @Column(name="choronic_diseases") private String choronicDiseases;
 * 
 * @Column(name="emergancy_level") private String emergancyLevel;
 * 
 * //bi-directional many-to-one association to FormHospital
 * 
 * @OneToMany(mappedBy="form") private List<FormHospital> formHospitals;
 * 
 * //bi-directional one-to-one association to Patient
 * 
 * @OneToOne(mappedBy="form") private Patient patient;
 * 
 * @Id
 * 
 * @Access(AccessType.PROPERTY)
 * 
 * @Override
 * 
 * @GeneratedValue(strategy = GenerationType.IDENTITY)
 * 
 * @Column(name = "form_id", unique = true, nullable = false) public long
 * getId() { return id; }
 * 
 * @Override public void setId(long id) { this.id = id; }
 * 
 * }
 */
