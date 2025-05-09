package com.hospital.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.hospital.models.Doctor;

public interface DoctorRepository extends MongoRepository<Doctor, String> {
}
