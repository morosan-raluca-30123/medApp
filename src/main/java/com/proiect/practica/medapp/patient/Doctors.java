package com.proiect.practica.medapp.patient;

import io.swagger.annotations.ApiModelProperty;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.security.Timestamp;
import java.util.Set;

@Entity
@Data
public class Doctors {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(hidden = true)
    private Integer doctorId;

    @ApiModelProperty(hidden = true)
    @ManyToMany
    private Set<Patient> patientId;
    @Column(updatable = false, nullable = false)
    @CreationTimestamp
    @ApiModelProperty(hidden = true)
    private Timestamp recordDate;

    private String description;

}
