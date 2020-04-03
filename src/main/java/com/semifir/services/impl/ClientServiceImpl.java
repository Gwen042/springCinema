package com.semifir.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.semifir.models.Client;
import com.semifir.repositories.ClientRepository;
import com.semifir.services.ClientService;


@Service
public class ClientServiceImpl implements ClientService {

	@Autowired private ClientRepository repo;
	
	@Override
	public Client save(Client entity) {
		return this.repo.save(entity);
	}

	@Override
	public List<Client> findAll() {
		return this.repo.findAll();
	}

	@Override
	public Optional<Client> findById(String id) {
		return this.repo.findById(id);
	}

	@Override
	public List<Client> findAllByNom(String nom) {
		return this.repo.findAllByNom(nom);
	}

	@Override
	public Client update(Client entity) {
		return this.repo.save(entity);
	}

	@Override
	public void delete(String id) {
		this.repo.deleteById(id);
	}
	
}
