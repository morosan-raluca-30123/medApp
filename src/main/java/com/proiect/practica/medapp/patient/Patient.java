package com.proiect.practica.medapp.patient;

import jakarta.persistence.*;
import lombok.Data;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.annotations.CreationTimestamp;

import java.security.Timestamp;
import java.util.Date;
import java.util.Set;

@Entity
@Data
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(hidden = true)
    private Integer patientId;


    private String name;

    private String surname;

    private String email;
    private Date birthday;
    private String phone;
    private String adress;
    @Column(updatable = false, nullable = false)
    @CreationTimestamp
    @ApiModelProperty(hidden = true)
    private Timestamp inregistration_date;
    @ApiModelProperty(hidden = true)
    private String medicalRecords;
    @ManyToMany
    @JoinTable(
            name = "doctor_patient", // Numele tabelei de legătură
            joinColumns = @JoinColumn(name = "patient_id"), // Coloana pentru ID-ul pacientului
            inverseJoinColumns = @JoinColumn(name = "doctor_id") // Coloana pentru ID-ul medicului
    )
    private Set<Doctors> doctors;


}
