package com.semifir.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.semifir.models.Client;


public interface ClientRepository extends MongoRepository<Client, String> {

	public List<Client> findAllByNom(String nom);
	
	
}
