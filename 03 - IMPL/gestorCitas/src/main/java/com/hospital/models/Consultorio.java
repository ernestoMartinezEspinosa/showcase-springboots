package com.hospital.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Document(collection = "consultorios")
@Data
public class Consultorio {
    @Id
    private String id;
    private int numero;
    private String piso;

    // Getters y Setters
}
