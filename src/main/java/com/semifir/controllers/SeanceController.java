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

import com.semifir.models.Seance;
import com.semifir.services.SeanceService;

@RestController
@CrossOrigin
@RequestMapping("seances")
public class SeanceController {

	@Autowired
	private SeanceService services;
	
	@GetMapping("")
	public List<Seance> findAll(){
		return this.services.findAll();
	}
	
	@PostMapping("")
	public Seance save(@RequestBody Seance entity) {
		return this.services.save(entity);
	}
	
	@PutMapping("")
	public Seance update(@RequestBody Seance entity) {
		return this.services.update(entity);
	}
	
	@DeleteMapping("")
	public void delete(@RequestBody Seance entity) {
		this.delete(entity.getId());
	}
	
	@DeleteMapping("{id}")
	public void delete(@PathVariable String id) {
		this.services.delete(id);
	}
	
	@GetMapping("{id}")
	public Optional<Seance> findById(@PathVariable String id) {
		return this.services.findById(id);
	}
	
//	@GetMapping("{id}/recette")
//	public Optional<Seance> (@PathVariable String id){
//		return this.services.(id);
//	}

//	@GetMapping("{id}/places")
//	public Optional<Seance> (@PathVariable String id){
//		return this.services.(id);
//	}
	
//	@GetMapping("horaire/{min}/{max}")
//	public Optional<Seance> (@PathVariable String id){
//		return this.services.(id);
//	}
	
//	@GetMapping("film/{nom}")
//	public  (@PathVariable String nom){
//		return this.services.(nom);
//	}

}