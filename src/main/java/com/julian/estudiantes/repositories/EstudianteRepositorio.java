/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.julian.estudiantes.repositories;

import com.julian.estudiantes.entities.Estudiante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author aguir
 */
@Repository
public interface EstudianteRepositorio extends JpaRepository<Estudiante, Long> {
    
}
