package com.semifir.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.semifir.DTO.CinemaDTO;
import com.semifir.models.Cinema;
import com.semifir.models.Salle;

@Service
public class CinemaServiceDTO {
	
	@Autowired
	private CinemaService cinemaService;
	@Autowired
	private SalleService salleService;

}
