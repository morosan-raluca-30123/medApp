package com.proiect.practica.medapp.patient;

import jakarta.persistence.*;
import lombok.Data;
import io.swagger.annotations.ApiModelProperty;

@Entity
@Data
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(hidden = true)
    private Integer id;

    private String username;

    @ApiModelProperty(hidden = true)
    private String password;

    private String role;
}
