package com.hospital.gestorCitas.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.hospital.gestorCitas.models.Consultorio;

public interface ConsultorioRepository extends MongoRepository<Consultorio, String> {
}
