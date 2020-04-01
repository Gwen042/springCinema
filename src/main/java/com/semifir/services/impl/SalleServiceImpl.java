package com.semifir.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.semifir.models.Salle;
import com.semifir.repositories.SalleRepository;
import com.semifir.services.SalleService;

@Service
public class SalleServiceImpl implements SalleService {

	@Autowired private SalleRepository repo;
	
	@Override
	public Salle save(Salle data) {
		return this.repo.save(data);
	}

}
