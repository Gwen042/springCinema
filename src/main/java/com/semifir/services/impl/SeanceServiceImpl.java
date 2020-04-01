package com.semifir.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.semifir.models.Seance;
import com.semifir.repositories.FilmRepository;
import com.semifir.repositories.SeanceRepository;
import com.semifir.services.SeanceService;

@Service
public class SeanceServiceImpl implements SeanceService {

	@Autowired private SeanceRepository repo;
	
	@Override
	public Seance save(Seance entity) {
		return this.repo.save(entity);
	}

	@Override
	public List<Seance> findAll() {
		return this.repo.findAll();
	}

	@Override
	public Optional<Seance> findById(String id) {
		return this.repo.findById(id);
	}

	@Override
	public Seance update(Seance entity) {
		return this.repo.save(entity);
	}

	@Override
	public void delete(String id) {
		this.repo.deleteById(id);
	}

}
