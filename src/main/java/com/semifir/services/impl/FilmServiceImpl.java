package com.semifir.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.semifir.models.Film;
import com.semifir.models.Seance;
import com.semifir.repositories.FilmRepository;
import com.semifir.services.FilmService;
import com.semifir.services.SeanceService;

@Service
public class FilmServiceImpl implements FilmService {

	@Autowired private FilmRepository repo;
	@Autowired private SeanceService seanceService;
	
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
	
	// Recette du film par l'id:
	
	@Override
	public float findByIdRecette(String id) {
		float recette = 0;
		Optional <Film> optF = this.findById(id);
		if(optF.isPresent()) {
			Film f = optF.get();
			Stream<Seance> seances = seanceService.findAllByFilmId(f.getId()).stream();
			recette = (float) seances.mapToDouble(s -> this.seanceService.recetteSeance(id)).sum();
		}
		return recette;
	}	

	
}
