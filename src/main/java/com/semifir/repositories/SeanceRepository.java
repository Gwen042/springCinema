package com.semifir.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;


import com.semifir.models.Film;
import com.semifir.models.Seance;

public interface SeanceRepository extends MongoRepository<Seance, String> {

	
	public List<Seance> findAllByFilmId(String id);
//	public List<Seance> findAllByFilmIn(Iterable<Film> films);
	public List<Seance> findAllByFilm(Film film);


}
