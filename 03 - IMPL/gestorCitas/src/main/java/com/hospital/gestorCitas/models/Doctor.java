package com.hospital.gestorCitas.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Document(collection = "doctores")
@Data
public class Doctor {
    @Id
    private String id;
    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String especialidad;

    // Getters y Setters
}
