package com.hospital.services;

import java.util.List;
import java.util.Optional;

import com.hospital.models.Doctor;

public interface DoctorService {
    List<Doctor> getAllDoctors();
    Optional<Doctor> getDoctorById(String id);
    Doctor createDoctor(Doctor doctor);
    Doctor updateDoctor(String id, Doctor doctor);
    void deleteDoctor(String id);
}
