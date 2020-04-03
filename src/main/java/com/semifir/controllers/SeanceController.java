package com.semifir.controllers;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.semifir.models.Assister;
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
	
	@PostMapping("{id}/assister/{cid}")
	private Assister assister (@PathVariable String id, @PathVariable String cid) {
		return this.services.assister(id, cid);
	}
	
	@GetMapping("{id}/recette")
	public float recetteSeance(@PathVariable String id){
		return this.services.recetteSeance(id);
	}

	@GetMapping("{id}/places")
	public int placeLibre(@PathVariable String id){
		return this.services.placeLibre(id);
	}
	
	@GetMapping("horaire/{min}/{max}")
	public List<Seance> findAllByDateBeetween(@PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)LocalDateTime min, 
											@PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)LocalDateTime max){
		return this.services.findAllByDateBetween(min, max);
	}
	
	@GetMapping("film/{id}")
	public List<Seance> findAllByFilmId(@PathVariable String id){
		return this.services.findAllByFilmId(id);
	}
	
	

}
