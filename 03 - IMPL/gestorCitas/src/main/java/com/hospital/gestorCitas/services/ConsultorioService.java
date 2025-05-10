package com.hospital.gestorCitas.services;

import java.util.List;
import java.util.Optional;

import com.hospital.gestorCitas.models.Consultorio;

public interface ConsultorioService {
    List<Consultorio> getAllConsultorios();
    Optional<Consultorio> getConsultorioById(String id);
    Consultorio createConsultorio(Consultorio consultorio);
    Consultorio updateConsultorio(String id, Consultorio consultorio);
    boolean deleteConsultorio(String id);
}
