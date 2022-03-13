/*
 * package com.example.quarantinefinder.entity;
 * 
 * import javax.persistence.Entity; import javax.persistence.JoinColumn; import
 * javax.persistence.ManyToOne;
 * 
 * import lombok.AllArgsConstructor; import lombok.Builder; import
 * lombok.Getter; import lombok.NoArgsConstructor; import lombok.Setter;
 * 
 * @Entity(name = "form_hospital")
 * 
 * @Getter
 * 
 * @Setter
 * 
 * @AllArgsConstructor
 * 
 * @NoArgsConstructor
 * 
 * @Builder public class FormHospital {
 * 
 * 
 * private byte acceptance;
 * 
 * // bi-directional many-to-one association to Form
 * 
 * @ManyToOne
 * 
 * @JoinColumn(name = "form_id") private Form form;
 * 
 * // bi-directional many-to-one association to Hospital
 * 
 * @ManyToOne
 * 
 * @JoinColumn(name = "hospital_id") private Hospital hospital;
 * 
 * }
 */