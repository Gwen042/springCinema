package com.semifir.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.semifir.models.Film;
import com.semifir.repositories.FilmRepository;
import com.semifir.services.FilmService;

@Service
public class FilmServiceImpl implements FilmService {

	@Autowired private FilmRepository repo;
	
	@Override
	public Film save(Film entity) {
		return this.repo.save(entity);
	}

	@Override
	public List<Film> findAll() {
		return this.repo.findAll();
	}

	@Override
	public Optional<Film> findById(String id) {
		return this.repo.findById(id);
	}

	@Override
	public List<Film> findAllByTitre(String titre) {
		return this.repo.findAllByTitre(titre);
	}

	@Override
	public Film update(Film entity) {
		return this.repo.save(entity);
	}

	@Override
	public void delete(String id) {
		this.repo.deleteById(id);
	}

}
