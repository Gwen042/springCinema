package com.semifir.services;

import java.util.List;
import java.util.Optional;

import com.semifir.models.Seance;

public interface SeanceService {

	public Seance save(Seance entity);
	public List<Seance> findAll();
	public Optional<Seance> findById(String id);
	public Seance update(Seance entity);
	public void delete(String id);

}
