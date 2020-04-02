package com.semifir.services;

import java.util.List;
import java.util.Optional;

import com.semifir.models.Assister;
import com.semifir.models.Film;
import com.semifir.models.Seance;

public interface SeanceService {

	public Seance save(Seance entity);
	public List<Seance> findAll();
	public Optional<Seance> findById(String id);
	public Seance update(Seance entity);
	public void delete(String id);
	public List<Seance> findAllByFilmTitre(String id);
	Assister assister(String id, String cid);
	public float recetteFilm(Film film);
	
	
	//public Film getSeances(String titre);
	//public Seance addClient(String sid, String cid);

}
