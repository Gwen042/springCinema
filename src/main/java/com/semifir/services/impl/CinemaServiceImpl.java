package com.semifir.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.semifir.DTO.CinemaDTO;
import com.semifir.models.Cinema;
import com.semifir.models.Salle;
import com.semifir.repositories.CinemaRepository;
import com.semifir.repositories.SalleRepository;
import com.semifir.services.CinemaService;
import com.semifir.services.SalleService;

@Service
public class CinemaServiceImpl implements CinemaService {

	@Autowired 
	private CinemaRepository repoCinema;
	@Autowired
	private SalleService salleService;
	
	@Override
	public CinemaDTO save(CinemaDTO data) {
		//génère cinema
		data.setCinema(this.save(data.getCinema()));
		//génère salles
		for (Salle salle: data.getSalles()) {
			salle.setCinema(data.getCinema());
			salle.setId(this.salleService.save(salle).getId());
		}
		return data;
	}


	@Override
	public Cinema save(Cinema data) {
		return this.repoCinema.save(data);
	}

}
