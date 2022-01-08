package com.bootrest.main.servicio;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bootrest.main.modelos.Estudiante;
import com.bootrest.main.repositorio.EstudianteRepositorio;

@Service
public class EstudianteServicioImp implements EstudianteServicio{

	@Autowired
	private EstudianteRepositorio repo;
	
	@Override
	@Transactional(readOnly=true)
	public List<Estudiante> findAll() {
		
		return repo.findAll();
	}

	@Override
	@Transactional(readOnly=true)
	public Optional<Estudiante> findById(int id) {
		
		return repo.findById(id);
	}

	@Override
	@Transactional
	public Estudiante save(Estudiante estudiante) {
		
		return repo.save(estudiante);
	}

	@Override
	@Transactional
	public void deleteById(int id) {
		repo.deleteById(id);
		
	}

}
