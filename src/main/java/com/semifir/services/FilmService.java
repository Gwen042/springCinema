package com.semifir.services;

import java.util.List;
import java.util.Optional;

import com.semifir.models.Film;

public interface FilmService {

	public Film save(Film entity);
	public List<Film> findAll();
	public Optional<Film> findById(String id);
	public List<Film> findAllByTitre(String titre);
	public Film update(Film entity);
	public void delete(String id);
	public float findByIdRecette(String id);

}
