package com.hospital.gestorCitas.repositories;

import com.hospital.gestorCitas.models.Cita;
import com.hospital.gestorCitas.models.Consultorio;
import com.hospital.gestorCitas.models.Doctor;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface CitaRepository extends MongoRepository<Cita, String> {

    // Consulta para encontrar todas las citas de un doctor en una fecha específica
    List<Cita> findByDoctorIdAndHorarioBetween(String doctorId, LocalDateTime start, LocalDateTime end);

    // Consulta para encontrar todas las citas de un consultorio en una fecha específica
    List<Cita> findByConsultorioIdAndHorarioBetween(String consultorioId, LocalDateTime start, LocalDateTime end);

    // Consulta para encontrar todas las citas de un paciente en un día
    List<Cita> findByNombrePacienteAndHorarioBetween(String nombrePaciente, LocalDateTime start, LocalDateTime end);

    // Consulta para encontrar una cita por el paciente y la hora exacta (para validar que no haya conflictos)
    Optional<Cita> findByNombrePacienteAndHorario(String nombrePaciente, LocalDateTime hora);

    // Consulta para contar cuántas citas tiene un doctor en un día
    long countByDoctorIdAndHorarioBetween(String doctorId, LocalDateTime start, LocalDateTime end);

    // Consulta para contar cuántas citas tiene un consultorio en un día
    long countByConsultorioIdAndHorarioBetween(String consultorioId, LocalDateTime start, LocalDateTime end);

    // Consulta para encontrar todas las citas para un día específico
    List<Cita> findByHorarioBetween(LocalDateTime start, LocalDateTime end);

    // Método para eliminar una cita por su ID
    void deleteById(String id);
    
	// Método para obtener citas de un doctor en un rango de fechas
    List<Cita> findByDoctorAndHorarioBetween(Doctor doctor, LocalDateTime startDate, LocalDateTime endDate);

	boolean existsByNombrePacienteAndHorario(String nombrePaciente, LocalDateTime horario);

	boolean existsByDoctorAndHorario(Doctor doctor, LocalDateTime horario);

	boolean existsByConsultorioAndHorario(Consultorio consultorio, LocalDateTime horario);

	boolean existsByNombrePacienteAndHorarioBetween(String nombrePaciente, LocalDateTime dosHorasAntes,
			LocalDateTime dosHorasDespues);

}
