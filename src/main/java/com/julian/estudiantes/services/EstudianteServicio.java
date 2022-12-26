package com.julian.estudiantes.services;

import com.julian.estudiantes.entities.Estudiante;
import com.julian.estudiantes.repositories.EstudianteRepositorio;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EstudianteServicio {

    @Autowired
    EstudianteRepositorio estudianteRepositorio;

    public List<Estudiante> listarTodos() {
        return estudianteRepositorio.findAll();
    }
    
    public Estudiante buscarPorId(Long id){
        return estudianteRepositorio.findById(id).get();
    }

    public void crear(String nombre, String apellido, String email) throws Exception {

        validarDatos(nombre, apellido, email);

        Estudiante estudiante = new Estudiante();

        estudiante.setNombre(nombre);
        estudiante.setApellido(apellido);
        estudiante.setEmail(email);

        estudianteRepositorio.save(estudiante);
    }

    public void modificar(Long id, String nombre, String apellido, String email) throws Exception {

        validarDatos(nombre, apellido, email);

        Estudiante estudiante = estudianteRepositorio.findById(id).get();
        estudiante.setNombre(nombre);
        estudiante.setApellido(apellido);
        estudiante.setEmail(email);
        
        estudianteRepositorio.save(estudiante);
    }
    
    public void eliminar (Long id){
        estudianteRepositorio.deleteById(id);
    }

    private void validarDatos(String nombre, String apellido, String email) throws Exception {

        if (nombre == null || nombre.isEmpty()) {
            throw new Exception("Nombre inválido");
        }

        if (apellido == null || apellido.isEmpty()) {
            throw new Exception("Apellido inválido");
        }

        if (email == null || email.isEmpty()) {
            throw new Exception("La dirección de correo electrónico es inválida");
        }
    }
}
