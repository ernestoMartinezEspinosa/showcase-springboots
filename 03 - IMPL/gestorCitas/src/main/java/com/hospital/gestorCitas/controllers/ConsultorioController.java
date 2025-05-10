package com.hospital.gestorCitas.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.hospital.gestorCitas.models.Consultorio;
import com.hospital.gestorCitas.services.ConsultorioService;

import java.util.List;

@Controller
@RequestMapping("/consultorios")
public class ConsultorioController {

    @Autowired
    private ConsultorioService consultorioService;

    @GetMapping
    public String listarConsultorios(Model model) {
        List<Consultorio> consultorios = consultorioService.getAllConsultorios();
        model.addAttribute("consultorios", consultorios);
        return "consultorios/list";
    }

    @GetMapping("/nuevo")
    public String mostrarFormularioNuevo(Model model) {
        model.addAttribute("consultorio", new Consultorio());
        return "consultorios/form";
    }

    @PostMapping("/guardar")
    public String guardarConsultorio(@ModelAttribute Consultorio consultorio) {
        consultorioService.createConsultorio(consultorio);
        return "redirect:/consultorios";
    }

    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable String id, Model model) {
        Consultorio consultorio = consultorioService.getConsultorioById(id).orElseThrow(() -> new RuntimeException("Consultorio no encontrado"));
        model.addAttribute("consultorio", consultorio);
        return "consultorios/form";
    }

    @PostMapping("/actualizar")
    public String actualizarConsultorio(@ModelAttribute Consultorio consultorio) {
        consultorioService.updateConsultorio(consultorio.getId(), consultorio);
        return "redirect:/consultorios";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarConsultorio(@PathVariable String id) {
        consultorioService.deleteConsultorio(id);
        return "redirect:/consultorios";
    }
}
