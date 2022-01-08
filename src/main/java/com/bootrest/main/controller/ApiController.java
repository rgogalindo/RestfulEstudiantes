package com.bootrest.main.controller;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bootrest.main.modelos.Estudiante;
import com.bootrest.main.servicio.EstudianteServicio;

@RestController
@RequestMapping("/user")
public class ApiController {

	@Autowired
	private EstudianteServicio serv;
	
	//obtener todos los estudiantes
	@GetMapping("/users")
	public List<Estudiante> getEstudiantes(){
		return serv.findAll();
	}
	
	//obtener un estudiante segun su ID
	@GetMapping("/users/{id}")
	public ResponseEntity<?> getEstudiante(@PathVariable int id){
		try {
			Estudiante estudiante = serv.findById(id).get();
			return ResponseEntity.status(HttpStatus.OK).body(estudiante);
		} catch (NoSuchElementException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error 404, no existe el usuario");
		}
	}
	
	@GetMapping("/users/notas/{id}")
	//obtener los notas de un estudiante segun su ID
	public ResponseEntity<?> getNotasEstudiante(@PathVariable int id){
		try {
			Estudiante estudiante = serv.findById(id).get();
			return ResponseEntity.status(HttpStatus.OK).body(estudiante.getNotas());
		} catch (NoSuchElementException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error 404, no existe el usuario");
		}
	}
	
	//guardar un estudiante
	@PostMapping("/save")
	public ResponseEntity<?> guardarEstudiante(@RequestBody Estudiante estudiante){
		return ResponseEntity.status(HttpStatus.OK).body(serv.save(estudiante));
	}
	
	//agregar notas a un estudiante segun su ID
	@PutMapping("/update/notas/{id}")
	public ResponseEntity<?> guardarNotasEstudiante(@PathVariable int id, @RequestBody Estudiante eNotas) {
		try {
			List<Integer> notas = eNotas.getNotas();
			Estudiante estudiante = serv.findById(id).get();
			List<Integer> notas1 = estudiante.getNotas();
			
			for(int i=0;i<notas.size();i++) {
				notas1.add(notas.get(i));
			}
			estudiante.setNotas(notas1);
			serv.save(estudiante);
			return ResponseEntity.status(HttpStatus.OK).body("Se agregaron las notas correctamente");
		} catch (NoSuchElementException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error 404, no existe el usuario");
		}
	}
	
	//actualizar un estudiante
	@PutMapping("/update/{id}")
	public ResponseEntity<?> actualizarEstudiante(@PathVariable int id, @RequestBody Estudiante estudiante){
		try {
			Estudiante e = serv.findById(id).get();
			e.setNombres(estudiante.getNombres());
			e.setApellidos(estudiante.getApellidos());
			e.setNotas(estudiante.getNotas());
			serv.save(e);
			return ResponseEntity.status(HttpStatus.OK).body("Se actualizo el usuario");
		} catch (NoSuchElementException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error 404, no existe el usuario");
		}
	}
	
	//borrar un estudiante segun su ID
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> borrarEstudiante(@PathVariable int id){
		try {
			serv.deleteById(id);
			return ResponseEntity.status(HttpStatus.OK).body("Se borro el usuario");
		} catch (NoSuchElementException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error 404, no existe el usuario");
		}
	}
	
	
}
