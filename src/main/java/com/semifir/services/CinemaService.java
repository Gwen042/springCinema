package com.semifir.services;

import com.semifir.DTO.CinemaDTO;
import com.semifir.models.Cinema;

public interface CinemaService {

	public Cinema saveCinema(Cinema entity);

	public CinemaDTO generate(CinemaDTO data);

}
