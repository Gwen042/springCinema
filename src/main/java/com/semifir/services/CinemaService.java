package com.semifir.services;

import com.semifir.DTO.CinemaDTO;
import com.semifir.models.Cinema;

public interface CinemaService {

	public Cinema save(Cinema data);

	public CinemaDTO save(CinemaDTO data);

}
