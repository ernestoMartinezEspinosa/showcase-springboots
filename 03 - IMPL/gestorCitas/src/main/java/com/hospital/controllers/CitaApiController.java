package com.hospital.controllers;

import com.hospital.models.Cita;
import com.hospital.services.CitaService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@Tag(name = "Citas", description = "Operaciones relacionadas con las citas médicas")
@RequestMapping("/api/citas")
public class CitaApiController {

    @Autowired
    private CitaService citaService;

    // Crear una nueva cita
    @Operation(summary = "Crear una nueva cita", description = "Este método permite crear una nueva cita médica.")
    @PostMapping
    public ResponseEntity<String> crearCita(@RequestBody Cita cita) {
        try {
            citaService.guardarCita(cita);
            return ResponseEntity.ok("Cita creada con éxito.");
        } catch (Exception e) {
            return ResponseEntity.status(400).body("Error al crear la cita: " + e.getMessage());
        }
    }

    // Obtener todas las citas
    @Operation(summary = "Obtener información de una cita", description = "Este método permite obtener una cita mediante su ID.")
    
    @GetMapping
    public List<Cita> obtenerTodasLasCitas() {
        return citaService.obtenerTodasLasCitas();
    }

    // Obtener una cita por su ID
    @GetMapping("/{id}")
    public ResponseEntity<Cita> obtenerCitaPorId(@PathVariable String id) {
        Optional<Cita> cita = citaService.obtenerCitaPorId(id);
        return cita.map(ResponseEntity::ok)
                   .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Actualizar una cita existente
    @PutMapping("/{id}")
    public ResponseEntity<String> actualizarCita(@PathVariable String id, @RequestBody Cita citaActualizada) {
        Optional<Cita> citaExistente = citaService.obtenerCitaPorId(id);
        if (citaExistente.isPresent()) {
            citaActualizada.setId(id);
            citaService.guardarCita(citaActualizada);
            return ResponseEntity.ok("Cita actualizada con éxito.");
        } else {
            return ResponseEntity.status(404).body("Cita no encontrada.");
        }
    }

    // Eliminar una cita por su ID
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarCita(@PathVariable String id) {
        Optional<Cita> cita = citaService.obtenerCitaPorId(id);
        if (cita.isPresent()) {
            citaService.eliminarCita(id);
            return ResponseEntity.ok("Cita eliminada con éxito.");
        } else {
            return ResponseEntity.status(404).body("Cita no encontrada.");
        }
    }
}
