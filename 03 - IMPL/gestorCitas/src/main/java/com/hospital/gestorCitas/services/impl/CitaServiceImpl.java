package com.hospital.gestorCitas.services.impl;

import com.hospital.gestorCitas.exceptions.CitaInvalidaException;
import com.hospital.gestorCitas.models.Cita;
import com.hospital.gestorCitas.models.Doctor;
import com.hospital.gestorCitas.repositories.CitaRepository;
import com.hospital.gestorCitas.services.CitaService;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class CitaServiceImpl implements CitaService {

    private final CitaRepository citaRepository;

    public CitaServiceImpl(CitaRepository citaRepository) {
        this.citaRepository = citaRepository;
    }

    @Override
    public Cita guardarCita(Cita cita) throws Exception {
    	Integer resp = validarCita(cita);
    	if (resp==201)
        return citaRepository.save(cita);
    	else
    		throw new RuntimeException("No se puede agendar la cita debido a las reglas de negocio.");
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
    public Integer validarCita(Cita cita) {
    	
    	// 0. Validar que la cita sea al menos 30 minutos después de la hora actual
        LocalDateTime ahoraMas30Min = LocalDateTime.now().plusMinutes(30);
        System.out.println("ahoraMas30Min:" +ahoraMas30Min);
        System.out.println("getHorario:" +cita.getHorario());
        if (cita.getHorario().isBefore(ahoraMas30Min)) {
            return 701; // La cita es demasiado pronto
        }
        
        // 1. Validar si el consultorio ya tiene una cita a la misma hora
        if (citaRepository.existsByConsultorioAndHorario(cita.getConsultorio(), cita.getHorario())) {
            return 702; // El consultorio está ocupado
        }

        // 2. Validar si el doctor ya tiene una cita a la misma hora
        if (citaRepository.existsByDoctorAndHorario(cita.getDoctor(), cita.getHorario())) {
            return 703; // El doctor ya tiene una cita
        }

        // 3. Validar si el paciente ya tiene una cita a la misma hora
        if (citaRepository.existsByNombrePacienteAndHorario(cita.getNombrePaciente(), cita.getHorario())) {
            return 704; // El paciente ya tiene una cita
        }

        // 4. Validar que un doctor no tenga más de 8 citas al día
        LocalDateTime inicioDelDia = cita.getHorario().toLocalDate().atStartOfDay();
        LocalDateTime finDelDia = cita.getHorario().toLocalDate().atTime(23, 59, 59);
        List<Cita> citasDelDoctor = citaRepository.findByDoctorAndHorarioBetween(cita.getDoctor(), inicioDelDia, finDelDia);
        if (citasDelDoctor.size() >= 8) {
            return 705; // El doctor ya tiene más de 8 citas
        }

        // 5. Validar que un paciente no tenga citas en el mismo día con menos de 2 horas de diferencia
        LocalDateTime dosHorasAntes = cita.getHorario().minusHours(2);
        LocalDateTime dosHorasDespues = cita.getHorario().plusHours(2);
        if (citaRepository.existsByNombrePacienteAndHorarioBetween(cita.getNombrePaciente(), dosHorasAntes, dosHorasDespues)) {
            return 706; // El paciente tiene una cita muy cerca
        }

        return 201;
    }
    
    @Override
    public Cita agendarCita(Cita cita) {
    	Integer resp = validarCita(cita);
        if (resp==201) {
            return citaRepository.save(cita);
        } else {
            throw new CitaInvalidaException("No se puede agendar la cita debido a las reglas de negocio.", resp);
        }
    }
    
    @Override
    public List<Cita> consultarCitasPorDoctorYFecha(Doctor doctor, LocalDateTime fecha) {
        // Calcular el inicio y fin del día de la fecha proporcionada
        LocalDateTime inicioDelDia = fecha.toLocalDate().atStartOfDay();
        LocalDateTime finDelDia = fecha.toLocalDate().atTime(23, 59, 59);
        
        // Usar el repositorio para obtener las citas del doctor en el rango de fechas
        return citaRepository.findByDoctorAndHorarioBetween(doctor, inicioDelDia, finDelDia);
    }
}
