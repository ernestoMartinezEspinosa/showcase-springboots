package com.hospital.services;

import com.hospital.models.Cita;
import java.util.List;
import java.util.Optional;

public interface CitaService {

    Cita guardarCita(Cita cita);

    List<Cita> obtenerTodasLasCitas();

    Optional<Cita> obtenerCitaPorId(String id);

    void eliminarCita(String id);
}
