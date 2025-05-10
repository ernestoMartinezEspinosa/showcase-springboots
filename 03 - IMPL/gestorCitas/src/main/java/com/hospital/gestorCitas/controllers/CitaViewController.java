package com.hospital.gestorCitas.controllers;

import com.hospital.gestorCitas.models.Cita;
import com.hospital.gestorCitas.models.Doctor;
import com.hospital.gestorCitas.services.CitaService;

import java.time.LocalDateTime;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/citas")
public class CitaViewController {

    @Autowired
    private CitaService citaService;
    
 // Crear una instancia de Logger para esta clase
    private static final Logger logger = LoggerFactory.getLogger(CitaViewController.class);

    @GetMapping("/nueva")
    public String mostrarFormularioNuevo(Model model) {
    	
    	logger.debug("Este es un mensaje de debug.");
        logger.info("Este es un mensaje de información.");
        logger.warn("Este es un mensaje de advertencia.");
        logger.error("Este es un mensaje de error.");
        
        model.addAttribute("cita", new Cita());
        model.addAttribute("mensaje", "");
        return "citas/nueva";
    }

    @PostMapping("/nueva")
    public String agendarCita(Cita cita, Model model) {
        try {
            Cita nuevaCita = citaService.agendarCita(cita);
            model.addAttribute("mensaje", "Cita agendada con éxito para " + nuevaCita.getHorario());
        } catch (Exception e) {
            model.addAttribute("mensaje", e.getMessage());
        }
        return "citas/nueva";
    }

    @GetMapping("/consultar")
    public String consultarCitas(Model model) {
        model.addAttribute("citas", citaService.consultarCitasPorDoctorYFecha(new Doctor(), LocalDateTime.now()));
        return "citas/consultar";
    }
    
    @GetMapping("/listar")
    public String listarCitas(Model model) {
        // Obtiene las citas desde el servicio
        List<Cita> citas = citaService.obtenerTodasLasCitas();

        // Agrega las citas al modelo para que Thymeleaf pueda acceder a ellas
        model.addAttribute("citas", citas);

        return "citas/listar";  // Retorna la vista index.html
    }
    
}
