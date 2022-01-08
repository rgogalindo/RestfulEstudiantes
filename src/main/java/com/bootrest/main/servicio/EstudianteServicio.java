package com.bootrest.main.servicio;

import java.util.List;
import java.util.Optional;

import com.bootrest.main.modelos.Estudiante;

public interface EstudianteServicio {

	public List<Estudiante> findAll();
	
	public Optional<Estudiante> findById(int id);
	
	public Estudiante save(Estudiante estudiante);
	
	public void deleteById(int id);
	
}
