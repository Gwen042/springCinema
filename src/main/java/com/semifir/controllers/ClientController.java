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

import com.semifir.models.Client;
import com.semifir.services.ClientService;

@RestController
@CrossOrigin
@RequestMapping("clients")
public class ClientController {

	@Autowired
	private ClientService services;
	
	@GetMapping("")
	public List<Client> findAll(){
		return this.services.findAll();
	}
	
	@PostMapping("")
	public Client save(@RequestBody Client entity) {
		return this.services.save(entity);
	}
	
	@PutMapping("")
	public Client update(@RequestBody Client entity) {
		return this.services.update(entity);
	}
	
	@DeleteMapping("")
	public void delete(@RequestBody Client entity) {
		this.delete(entity.getId());
	}
	
	@DeleteMapping("{id}")
	public void delete(@PathVariable String id) {
		this.services.delete(id);
	}
	
	@GetMapping("{id}")
	public Optional<Client> findById(@PathVariable String id) {
		return this.services.findById(id);
	}
	
}


