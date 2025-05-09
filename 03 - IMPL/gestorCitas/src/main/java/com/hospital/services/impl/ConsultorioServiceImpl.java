package com.hospital.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hospital.models.Consultorio;
import com.hospital.repositories.ConsultorioRepository;
import com.hospital.services.ConsultorioService;

import java.util.List;
import java.util.Optional;

@Service
public class ConsultorioServiceImpl implements ConsultorioService {

    @Autowired
    private ConsultorioRepository consultorioRepository;

    @Override
    public List<Consultorio> getAllConsultorios() {
        return consultorioRepository.findAll();
    }

    @Override
    public Optional<Consultorio> getConsultorioById(String id) {
        return consultorioRepository.findById(id);
    }

    @Override
    public Consultorio createConsultorio(Consultorio consultorio) {
        return consultorioRepository.save(consultorio);
    }

    @Override
    public Consultorio updateConsultorio(String id, Consultorio consultorio) {
        if (consultorioRepository.existsById(id)) {
            consultorio.setId(id); // Asegurarse de que el ID no cambie
            return consultorioRepository.save(consultorio);
        } else {
            throw new RuntimeException("Consultorio no encontrado con ID: " + id);
        }
    }

    @Override
    public void deleteConsultorio(String id) {
        if (consultorioRepository.existsById(id)) {
            consultorioRepository.deleteById(id);
        } else {
            throw new RuntimeException("Consultorio no encontrado con ID: " + id);
        }
    }
}
