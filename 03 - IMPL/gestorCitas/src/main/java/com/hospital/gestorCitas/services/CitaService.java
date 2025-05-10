package com.hospital.gestorCitas.services;

import com.hospital.gestorCitas.models.Cita;
import com.hospital.gestorCitas.models.Doctor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface CitaService {

    Cita guardarCita(Cita cita) throws Exception;

    List<Cita> obtenerTodasLasCitas();

    Optional<Cita> obtenerCitaPorId(String id);

    void eliminarCita(String id);
    
    Integer validarCita(Cita cita);
    
    Cita agendarCita(Cita cita);
    
    List<Cita> consultarCitasPorDoctorYFecha(Doctor doctor, LocalDateTime fecha);
}
