package com.semifir.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.semifir.DTO.CinemaDTO;
import com.semifir.services.CinemaService;

@RestController
@CrossOrigin
@RequestMapping("cinemas")
public class CinemaController {

	@Autowired
	private CinemaService service;
	
	@PostMapping("")
	public void saveCinema(@RequestBody CinemaDTO data) {
		this.service.saveCinema(data);
	}
}
