package com.proiect.practica.medapp.patient;

import com.proiect.practica.medapp.appointments.Appointment;
import com.proiect.practica.medapp.doctors.Doctor;
import jakarta.persistence.*;
import lombok.Data;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Set;

@Entity
@Data
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(hidden = true)
    private Integer id;

    private String firstName;

    private String lastName;

    private String email;
    private Date birthday;
    private String phone;
    private String adress;
    @Column(updatable = false, nullable = false)
    @CreationTimestamp
   // @ApiModelProperty(hidden = true)
    private Timestamp inregistrationDate;
    @ApiModelProperty(hidden = true)
    private String medicalRecords;

    @OneToMany(mappedBy = "patient")
    @ApiModelProperty(hidden = true)
    private Set<Appointment> appointments;


}
