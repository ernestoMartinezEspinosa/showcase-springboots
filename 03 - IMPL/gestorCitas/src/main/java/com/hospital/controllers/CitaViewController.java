package com.hospital.controllers;

import com.hospital.models.Cita;
import com.hospital.models.Doctor;
import com.hospital.services.CitaService;

import java.time.LocalDateTime;

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

    @GetMapping("/nueva")
    public String mostrarFormularioNuevo(Model model) {
        model.addAttribute("cita", new Cita());
        model.addAttribute("mensaje", "");
        return "citas/nueva";
    }

    @PostMapping("/nueva")
    public String agendarCita(Cita cita, Model model) {
        try {
            Cita nuevaCita = citaService.agendarCita(cita);
            model.addAttribute("mensaje", "Cita agendada con éxito para " + nuevaCita.getFechaHora());
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
}
