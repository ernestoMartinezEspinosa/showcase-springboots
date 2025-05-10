package com.hospital.gestorCitas.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hospital.gestorCitas.models.Doctor;
import com.hospital.gestorCitas.repositories.DoctorRepository;
import com.hospital.gestorCitas.services.DoctorService;

import java.util.List;
import java.util.Optional;

@Service
public class DoctorServiceImpl implements DoctorService {

    @Autowired
    private DoctorRepository doctorRepository;

    @Override
    public List<Doctor> getAllDoctors() {
        return doctorRepository.findAll();
    }

    @Override
    public Optional<Doctor> getDoctorById(String id) {
        return doctorRepository.findById(id);
    }

    @Override
    public Doctor createDoctor(Doctor doctor) {
        return doctorRepository.save(doctor);
    }

    @Override
    public Doctor updateDoctor(String id, Doctor doctor) {
        if (doctorRepository.existsById(id)) {
            doctor.setId(id);
            return doctorRepository.save(doctor);
        } else {
            throw new RuntimeException("Doctor no encontrado con ID: " + id);
        }
    }

    @Override
    public void deleteDoctor(String id) {
        if (doctorRepository.existsById(id)) {
            doctorRepository.deleteById(id);
        } else {
            throw new RuntimeException("Doctor no encontrado con ID: " + id);
        }
    }
}
