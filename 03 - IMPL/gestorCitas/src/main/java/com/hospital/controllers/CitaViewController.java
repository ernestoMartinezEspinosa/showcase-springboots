package com.hospital.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hospital.models.Cita;

@Controller
@RequestMapping("/citas")
public class CitaViewController {


	@GetMapping("/nueva")
    public String mostrarFormularioNuevo(Model model) {
        model.addAttribute("cita", new Cita());
        return "citas/nueva";
    }

    @GetMapping("/editar")
    public String editarCita() {
        return "editar";
    }

    @GetMapping("/consultar")
    public String consultarCitas() {
        return "consultar";
    }

}
