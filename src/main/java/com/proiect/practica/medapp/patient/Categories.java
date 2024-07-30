package com.proiect.practica.medapp.patient;

import io.swagger.annotations.ApiModelProperty;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Categories {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(hidden = true)
    private Integer categoryId;

    private String name;
    private String description;



}
