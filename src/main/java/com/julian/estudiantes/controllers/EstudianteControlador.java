/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.julian.estudiantes.controllers;

import com.julian.estudiantes.entities.Estudiante;
import com.julian.estudiantes.services.EstudianteServicio;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author aguir
 */
@Controller
@RequestMapping("/")
public class EstudianteControlador {
    
    @Autowired
    private EstudianteServicio estudianteServicio;
    
    @GetMapping("/")
    public String listarEstudiantes(ModelMap modelo){
        List<Estudiante> estudiantes = estudianteServicio.listarTodos();
        modelo.put("estudiantes", estudiantes);
        
        return "index.html";
    }
    
    @GetMapping("/registro")
    public String registroEstudiante(){
        return "registrar.html";
    }
    
    @PostMapping("/registro")
    public String registroEstudiante(@RequestParam String nombre, @RequestParam String apellido,
            @RequestParam String email,
            RedirectAttributes attr){
        
        try {
            estudianteServicio.crear(nombre, apellido, email);
            attr.addFlashAttribute("exito", "Estudiante creado exitosamente.");
            return "redirect:/";
        } catch (Exception e) {
            attr.addFlashAttribute("error", e.getMessage());
            attr.addFlashAttribute("nombre", nombre);
            attr.addFlashAttribute("apellido", apellido);
            attr.addFlashAttribute("email", email);
            
            return "redirect:/registro";
        }
        
    }
    
    @GetMapping("/editar/{id}")
    public String editarEstudiante(@PathVariable("id") Long id, ModelMap modelo){
        
        Estudiante estudiante = estudianteServicio.buscarPorId(id);
        modelo.put("estudiante", estudiante);
        
        return "modificar.html";
    }
    
    @GetMapping("/eliminar/{id}")
    public String eliminarEstudiante(@PathVariable("id") Long id, ModelMap modelo){
        
        Estudiante estudiante = estudianteServicio.buscarPorId(id);
        modelo.put("estudiante", estudiante);
        return "eliminar.html";
    }
    
    @PostMapping("/eliminar/{id}")
    public String eliminarEstudiante(@PathVariable("id") Long id, RedirectAttributes attr){
        estudianteServicio.eliminar(id);
        attr.addFlashAttribute("exito", "Estudiante eliminado.");
        return "redirect:/";
    }
    
    @PostMapping("editar")
    public String editarEstudiante(@RequestParam Long id, @RequestParam String nombre,
            @RequestParam String apellido, @RequestParam String email,
            ModelMap modelo, RedirectAttributes attr){
        
        try {
            estudianteServicio.modificar(id, nombre, apellido, email);
            attr.addFlashAttribute("exito", "Estudiante modificado con éxito");
            return "redirect:/";
        } catch (Exception ex) {
            attr.addFlashAttribute("error", ex.getMessage());
            return "redirect:/modificar/" + id;
        }
    }
    
    
}
