package com.semifir.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.semifir.models.Film;
import com.semifir.services.FilmService;

@RestController
@CrossOrigin
@RequestMapping("films")
public class FilmController {

	@Autowired
	private FilmService services;
	
	@GetMapping("")
	public List<Film> findAll(){
		return this.services.findAll();
	}
	
	@PostMapping("")
	public Film save(@RequestBody Film entity) {
		return this.services.save(entity);
	}
	
	@PutMapping("")
	public Film update(@RequestBody Film entity) {
		return this.services.update(entity);
	}
	
	@DeleteMapping("")
	public void delete(@RequestBody Film entity) {
		this.delete(entity.getId());
	}
	
	@DeleteMapping("{id}")
	public void delete(@PathVariable String id) {
		this.services.delete(id);
	}
	
	@GetMapping("{id}")
	public Optional<Film> findById(@PathVariable String id) {
		return this.services.findById(id);
	}
	
	@GetMapping("{id}/recette")
	public float findByIdRecette(@PathVariable String id){
		return this.services.findByIdRecette(id);
	}
	
	@GetMapping("titres/{titre}")
	public List<Film> findAllByTitre(@PathVariable String titre) {
		return this.services.findAllByTitre(titre);
	}
}
