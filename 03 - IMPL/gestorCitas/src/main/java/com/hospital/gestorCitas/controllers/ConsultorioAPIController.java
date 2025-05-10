package com.hospital.gestorCitas.controllers;

import com.hospital.gestorCitas.models.Consultorio;
import com.hospital.gestorCitas.services.ConsultorioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/consultorios")
public class ConsultorioAPIController {

    private final ConsultorioService consultorioService;

    @Autowired
    public ConsultorioAPIController(ConsultorioService consultorioService) {
        this.consultorioService = consultorioService;
    }

    // Obtener todos los consultorios
    @GetMapping
    public ResponseEntity<List<Consultorio>> getAllConsultorios() {
        List<Consultorio> consultorios = consultorioService.getAllConsultorios();
        return new ResponseEntity<>(consultorios, HttpStatus.OK);
    }

    // Obtener un consultorio por ID
    @GetMapping("/{id}")
    public ResponseEntity<Consultorio> getConsultorioById(@PathVariable("id") String id) {
        Optional<Consultorio> consultorio = consultorioService.getConsultorioById(id);
        return consultorio.map(ResponseEntity::ok)
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Crear un nuevo consultorio
    @PostMapping
    public ResponseEntity<Consultorio> createConsultorio(@RequestBody Consultorio consultorio) {
        Consultorio createdConsultorio = consultorioService.createConsultorio(consultorio);
        return new ResponseEntity<>(createdConsultorio, HttpStatus.CREATED);
    }

    // Actualizar un consultorio
    @PutMapping("/{id}")
    public ResponseEntity<Consultorio> updateConsultorio(
            @PathVariable("id") String id, 
            @RequestBody Consultorio consultorio
    ) {
        Consultorio updatedConsultorio = consultorioService.updateConsultorio(id, consultorio);
        return updatedConsultorio != null 
                ? new ResponseEntity<>(updatedConsultorio, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // Eliminar un consultorio
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteConsultorio(@PathVariable("id") String id) {
        boolean isDeleted = consultorioService.deleteConsultorio(id);
        return isDeleted 
                ? new ResponseEntity<>(HttpStatus.NO_CONTENT) 
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
