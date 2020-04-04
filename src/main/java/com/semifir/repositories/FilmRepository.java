package com.semifir.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.semifir.models.Film;

public interface FilmRepository extends MongoRepository<Film, String> {

	public List<Film> findAllByTitre(String titre);
	public List<Film> findAllByGenre(String genre);
	public List<Film> findAllByAgeLimite(int ageLimite);
	public Optional<Film> findByTitre(String titre);
	
}
