package com.hospital.gestorCitas.models;

import org.springframework.data.annotation.Id;

public class Paciente {

    @Id
    private String id;
    private String nombre;
    private String apellido;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

    // Getters y Setters
    
}
