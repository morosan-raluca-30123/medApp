package com.proiect.practica.medapp.doctors;

import com.proiect.practica.medapp.appointments.Appointment;
import com.proiect.practica.medapp.patient.Patient;
import io.swagger.annotations.ApiModelProperty;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;
import java.util.Set;

@Entity
@Data
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(hidden = true)
    private Integer id;

    private String firstName;
    private String lastName;
    private String specialization;
    private String phone;


    @Column(updatable = false, nullable = false)
    @CreationTimestamp
    @ApiModelProperty(hidden = true)
    private Timestamp recordDate;

    @OneToMany(mappedBy = "doctor")
    @ApiModelProperty(hidden = true)
    private Set<Appointment> appointments;

    private String description;

}
