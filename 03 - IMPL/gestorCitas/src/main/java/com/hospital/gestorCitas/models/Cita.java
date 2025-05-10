package com.hospital.gestorCitas.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

import java.time.LocalDateTime;

@Document(collection = "citas")
@Data
public class Cita {
    @Id
    private String id;
    private LocalDateTime horario;

    @DBRef
    private Doctor doctor;

    @DBRef
    private Consultorio consultorio;

    private String nombrePaciente;
    
    private String motivo;

    // Getters y Setters por lombok
    
}
