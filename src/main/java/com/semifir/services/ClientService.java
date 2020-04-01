package com.semifir.services;

import java.util.List;
import java.util.Optional;

import com.semifir.models.Client;


public interface ClientService {

	public Client save(Client entity);
	public List<Client> findAll();
	public Optional<Client> findById(String id);
	public List<Client> findAllByNom(String nom);
	public Client update(Client entity);
	public void delete(String id);


}
