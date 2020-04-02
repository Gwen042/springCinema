package com.semifir.services.impl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.semifir.models.Assister;
import com.semifir.models.Cinema;
import com.semifir.models.Client;
import com.semifir.models.Film;
import com.semifir.models.Seance;
import com.semifir.repositories.AssisterRepository;
import com.semifir.repositories.ClientRepository;
import com.semifir.repositories.FilmRepository;
import com.semifir.repositories.SeanceRepository;
import com.semifir.services.ClientService;
import com.semifir.services.SeanceService;


@Service
public class SeanceServiceImpl implements SeanceService {

	@Autowired private SeanceRepository repo;
	@Autowired private AssisterRepository assisterRepo;
	@Autowired private ClientService clientService;
	@Autowired private SeanceService seanceService;
	
	@Override
	public Seance save(Seance entity) {
		return this.repo.save(entity);
	}

	@Override
	public List<Seance> findAll() {
		return this.repo.findAll();
	}

	@Override
	public Optional<Seance> findById(String id) {
		return this.repo.findById(id);
	}

	@Override
	public Seance update(Seance entity) {
		return this.repo.save(entity);
	}

	@Override
	public void delete(String id) {
		this.repo.deleteById(id);
	}
	
	@Override
	public Assister assister(String id, String cid) {
		Assister res = null;
		
		Optional<Seance> optS = this.findById(id);
		if (optS.isPresent()) {
			Seance seance = optS.get();
			
			Optional<Client> optC = this.clientService.findById(cid);
			if (optC.isPresent()) {
				Client c = optC.get();
				res = new Assister(this.calculPrix(seance, c), c);
				this.save(seance);
				//this.assisterRepo.save(res);
			}
		}
		return res;
	}
	
	//calcul du prix dans Assister:
	
	private float calculPrix(Seance s, Client c) {
		float resultPrix = 10;
		
		//prix en fonction du type de séance
		if(s.getType().equals("3D")) 
			resultPrix += 3;
		else if(s.getType().equals("IMAX"))
			resultPrix += 6;
		else if (s.getType().equals("4DX"))
			resultPrix += 8;
		
		//prix en fonction de l'age et du tarif étudiant du client
		if (c.getNaissance().compareTo(LocalDate.now()) < 10)
			resultPrix -= 4;
		if (c.isEtudiant())
			resultPrix -= 2;
		
		return resultPrix;
	}

	//Calcul de la recette du film:
	
	@Override
	public float recetteFilm(Film film) {
		List<Seance> seances = this.repo.findAllByFilm(film);
		return (float) seances.stream().mapToDouble(s -> {
			return s.getClients().stream().mapToDouble(c -> {
				return c.getPrix();
			}).sum();
		}).sum();
	}
	
	//Calcul de la recette d'une séance:
	
	@Override
	public float recetteSeance(String id) {
		Optional<Seance> optS = this.findById(id);
		float recetteSeance = 0;
		if(optS.isPresent()) {
			Seance s = optS.get();
			List<Assister> clients = s.getClients();
			
			for(Assister a : clients) {
				recetteSeance += a.getPrix();
			}
		}
		return recetteSeance;
	}
	
	//Trouver les séances pour un film (ici id)...A revoir(avec le titre?):
	
	@Override
	public List<Seance>findAllByFilmId(String id) {
		return this.repo.findAllByFilmId(id);
	}
	
	//Trouver le nb de places libres:
	
	@Override
	public int placeLibre(String id) {
		Optional<Seance> optS = this.findById(id);
		int pl = 0;
		if(optS.isPresent()) {
			Seance s = optS.get();
			if(s.getSalle() != null) {
				pl = s.getSalle().getPlace() - s.getClients().size();
			}
		}
		return pl;
	}
	
	//Trouver des séances dans une plage horaire:
/*	
	@Override
	public List<Seance> findByDateBetween(LocalDateTime min, LocalDateTime max){
		return this.repo.findByDateBetween(min, max);
	}
*/
}
