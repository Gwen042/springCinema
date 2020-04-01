package com.semifir.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.semifir.models.Film;

public interface FilmRepository extends MongoRepository<Film, String> {

	public List<Film> findAllByTitre(String titre);
	
}
