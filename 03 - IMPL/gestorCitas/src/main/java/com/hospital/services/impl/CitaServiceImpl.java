package com.hospital.services.impl;

import com.hospital.models.Cita;
import com.hospital.models.Doctor;
import com.hospital.repositories.CitaRepository;
import com.hospital.services.CitaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class CitaServiceImpl implements CitaService {

    private final CitaRepository citaRepository;

    @Autowired
    public CitaServiceImpl(CitaRepository citaRepository) {
        this.citaRepository = citaRepository;
    }

    @Override
    public Cita guardarCita(Cita cita) {
        return citaRepository.save(cita);
    }

    @Override
    public List<Cita> obtenerTodasLasCitas() {
        return citaRepository.findAll();
    }

    @Override
    public Optional<Cita> obtenerCitaPorId(String id) {
        return citaRepository.findById(id);
    }

    @Override
    public void eliminarCita(String id) {
        citaRepository.deleteById(id);
    }
    
    @Override
    public boolean validarCita(Cita cita) {
        // 1. Validar si el consultorio ya tiene una cita a la misma hora
        if (citaRepository.existsByConsultorioAndFechaHora(cita.getConsultorio(), cita.getFechaHora())) {
            return false; // El consultorio está ocupado
        }

        // 2. Validar si el doctor ya tiene una cita a la misma hora
        if (citaRepository.existsByDoctorAndFechaHora(cita.getDoctor(), cita.getFechaHora())) {
            return false; // El doctor ya tiene una cita
        }

        // 3. Validar si el paciente ya tiene una cita a la misma hora
        if (citaRepository.existsByPacienteAndFechaHora(cita.getPaciente(), cita.getFechaHora())) {
            return false; // El paciente ya tiene una cita
        }

        // 4. Validar que un doctor no tenga más de 8 citas al día
        LocalDateTime inicioDelDia = cita.getFechaHora().toLocalDate().atStartOfDay();
        LocalDateTime finDelDia = cita.getFechaHora().toLocalDate().atTime(23, 59, 59);
        List<Cita> citasDelDoctor = citaRepository.findByDoctorAndFechaHoraBetween(cita.getDoctor(), inicioDelDia, finDelDia);
        if (citasDelDoctor.size() >= 8) {
            return false; // El doctor ya tiene más de 8 citas
        }

        // 5. Validar que un paciente no tenga citas en el mismo día con menos de 2 horas de diferencia
        LocalDateTime dosHorasAntes = cita.getFechaHora().minusHours(2);
        LocalDateTime dosHorasDespues = cita.getFechaHora().plusHours(2);
        if (citaRepository.existsByPacienteAndFechaHoraBetween(cita.getPaciente(), dosHorasAntes, dosHorasDespues)) {
            return false; // El paciente tiene una cita muy cerca
        }

        return true;
    }
    
    @Override
    public Cita agendarCita(Cita cita) {
        if (validarCita(cita)) {
            return citaRepository.save(cita);
        } else {
            throw new RuntimeException("No se puede agendar la cita debido a las reglas de negocio.");
        }
    }
    
    @Override
    public List<Cita> consultarCitasPorDoctorYFecha(Doctor doctor, LocalDateTime fecha) {
        // Calcular el inicio y fin del día de la fecha proporcionada
        LocalDateTime inicioDelDia = fecha.toLocalDate().atStartOfDay();
        LocalDateTime finDelDia = fecha.toLocalDate().atTime(23, 59, 59);
        
        // Usar el repositorio para obtener las citas del doctor en el rango de fechas
        return citaRepository.findByDoctorAndFechaHoraBetween(doctor, inicioDelDia, finDelDia);
    }
}
