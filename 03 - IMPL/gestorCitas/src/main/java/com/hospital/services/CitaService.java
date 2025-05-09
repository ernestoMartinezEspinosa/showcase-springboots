package com.hospital.services;

import com.hospital.models.Cita;
import com.hospital.models.Doctor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface CitaService {

    Cita guardarCita(Cita cita);

    List<Cita> obtenerTodasLasCitas();

    Optional<Cita> obtenerCitaPorId(String id);

    void eliminarCita(String id);
    
    boolean validarCita(Cita cita);
    
    Cita agendarCita(Cita cita);
    
    List<Cita> consultarCitasPorDoctorYFecha(Doctor doctor, LocalDateTime fecha);
}
