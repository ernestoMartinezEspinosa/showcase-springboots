package com.hospital.gestorCitas.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;


import com.hospital.gestorCitas.models.Doctor;

public interface DoctorRepository extends MongoRepository<Doctor, String> {

}
