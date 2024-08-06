package com.proiect.practica.medapp.appointments;

import com.proiect.practica.medapp.doctors.Doctor;
import com.proiect.practica.medapp.patient.Patient;
import jakarta.persistence.*;
import lombok.Data;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;
import java.util.Set;


@Entity
@Data
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(hidden = true)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "patient_id", nullable = false)
    private Patient patient;

    @ManyToOne
    @JoinColumn(name = "doctor_id", nullable = false)
    private Doctor doctor;

    @Column(updatable = false, nullable = false)
    @CreationTimestamp
    @ApiModelProperty(hidden = true)
    private Timestamp appointmentDate;

    private String reason;

    @ApiModelProperty(hidden = true)
    private String status;


}
