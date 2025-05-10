package com.hospital.gestorCitas.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.hospital.gestorCitas.exceptions.CitaInvalidaException;
import com.hospital.gestorCitas.models.Cita;
import com.hospital.gestorCitas.services.CitaService;

import java.util.List;
import java.util.Optional;
import com.hospital.gestorCitas.models.ResponseGral;

@RestController
@Tag(name = "Citas", description = "Operaciones relacionadas con las citas médicas")
@RequestMapping("/api/citas")
public class CitaApiController {

	@Autowired
	private CitaService citaService;

	// Crear una instancia de Logger para esta clase
	private static final Logger logger = LoggerFactory.getLogger(CitaApiController.class);

	// Crear una nueva cita
	@Operation(summary = "Crear una nueva cita", description = "Este método permite crear una nueva cita médica.")
	@PostMapping
	public ResponseEntity<ResponseGral> crearCita(@RequestBody Cita cita) {
		try {
			citaService.agendarCita(cita);
			ResponseGral oksMsg = new ResponseGral("Cita creada con éxito.",201);
			return ResponseEntity.ok(oksMsg);
		} catch (CitaInvalidaException e) {
			logger.error(e.getMessage());
			ResponseGral errorMsg = new ResponseGral(e.getMessage(), e.getCodigo());
			return ResponseEntity.status(400).body(errorMsg);
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
		return cita.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
	}

	// Actualizar una cita existente
	@PutMapping("/{id}")
	public ResponseEntity<ResponseGral> actualizarCita(@PathVariable String id, @RequestBody Cita citaActualizada) {

		try {
			Optional<Cita> citaExistente = citaService.obtenerCitaPorId(id);
			if (citaExistente.isPresent()) {
				citaActualizada.setId(id);
				citaService.guardarCita(citaActualizada);
				ResponseGral oksMsg = new ResponseGral("Cita actualizada con éxito.",201);
				return ResponseEntity.ok(oksMsg);
			} else {
				ResponseGral errorMsg = new ResponseGral("No se logró actualizar la cita", 708);
				return ResponseEntity.status(400).body(errorMsg);
			}
		} catch (Exception e) {
			ResponseGral errorMsg = new ResponseGral("Error al crear la cita: " + e.getMessage(), 708);
			return ResponseEntity.status(400).body(errorMsg);
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
