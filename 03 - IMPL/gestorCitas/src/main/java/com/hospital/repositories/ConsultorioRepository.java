package com.hospital.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.hospital.models.Consultorio;

public interface ConsultorioRepository extends MongoRepository<Consultorio, String> {
}
