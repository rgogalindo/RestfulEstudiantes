package com.bootrest.main.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bootrest.main.modelos.Estudiante;

public interface EstudianteRepositorio extends JpaRepository<Estudiante, Integer>{

}
