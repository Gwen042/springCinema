package com.semifir.DTO;

import java.util.ArrayList;
import java.util.List;

import com.semifir.models.Cinema;
import com.semifir.models.Salle;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CinemaDTO {

	private Cinema cinema;
	private List<Salle> salles = new ArrayList<>();
}
