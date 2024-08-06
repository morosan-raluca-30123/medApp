package com.proiect.practica.medapp.materials;



import io.swagger.annotations.ApiModelProperty;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;


@Entity
@Data
public class Material {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(hidden = true)
    private Integer materialId;

    private String title;
    private String description;

    private String url;
    @Column(updatable = false, nullable = false)
    @CreationTimestamp
    @ApiModelProperty(hidden = true)
    private Timestamp additionData;


}
