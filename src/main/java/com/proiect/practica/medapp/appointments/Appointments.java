package com.proiect.practica.medapp.appointments;

import com.proiect.practica.medapp.doctors.Doctors;
import com.proiect.practica.medapp.patient.Patient;
import jakarta.persistence.*;
import lombok.Data;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.annotations.CreationTimestamp;

import java.security.Timestamp;


@Entity
@Data
public class Appointments {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(hidden = true)
    private Integer id;

    @ManyToOne
    @ApiModelProperty(hidden = true)
    private Patient patientId;

    @OneToOne
    @ApiModelProperty(hidden = true)
    private Doctors doctorId;

    @Column(updatable = false, nullable = false)
    @CreationTimestamp
    @ApiModelProperty(hidden = true)
    private Timestamp appointmentDate;

    private String reason;

    @ApiModelProperty(hidden = true)
    private String status;
}
