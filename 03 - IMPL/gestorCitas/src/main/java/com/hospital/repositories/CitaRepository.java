package com.hospital.repositories;

import com.hospital.models.Cita;
import com.hospital.models.Doctor;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface CitaRepository extends MongoRepository<Cita, String> {

    // Consulta para encontrar todas las citas de un doctor en una fecha específica
    List<Cita> findByDoctorIdAndHoraBetween(String doctorId, LocalDateTime start, LocalDateTime end);

    // Consulta para encontrar todas las citas de un consultorio en una fecha específica
    List<Cita> findByConsultorioIdAndHoraBetween(String consultorioId, LocalDateTime start, LocalDateTime end);

    // Consulta para encontrar todas las citas de un paciente en un día
    List<Cita> findByNombrePacienteAndHoraBetween(String nombrePaciente, LocalDateTime start, LocalDateTime end);

    // Consulta para encontrar una cita por el paciente y la hora exacta (para validar que no haya conflictos)
    Optional<Cita> findByNombrePacienteAndHora(String nombrePaciente, LocalDateTime hora);

    // Consulta para contar cuántas citas tiene un doctor en un día
    long countByDoctorIdAndHoraBetween(String doctorId, LocalDateTime start, LocalDateTime end);

    // Consulta para contar cuántas citas tiene un consultorio en un día
    long countByConsultorioIdAndHoraBetween(String consultorioId, LocalDateTime start, LocalDateTime end);

    // Consulta para encontrar todas las citas para un día específico
    List<Cita> findByHoraBetween(LocalDateTime start, LocalDateTime end);

    // Método para eliminar una cita por su ID
    void deleteById(String id);
    
	// Método para obtener citas de un doctor en un rango de fechas
    List<Cita> findByDoctorAndFechaHoraBetween(Doctor doctor, LocalDateTime startDate, LocalDateTime endDate);

}
