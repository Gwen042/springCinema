package com.semifir.models;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cinema {

	@Id
	private String id;
	private String nom;
	private String adresse;
	
	@DBRef
	private List<Salle> salles = new ArrayList<>();
	
}
