package com.semifir.services.impl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.semifir.models.Assister;
import com.semifir.models.Client;
import com.semifir.models.Film;
import com.semifir.models.Seance;
import com.semifir.repositories.AssisterRepository;
import com.semifir.repositories.SeanceRepository;
import com.semifir.services.ClientService;
import com.semifir.services.FilmService;
import com.semifir.services.SeanceService;


@Service
public class SeanceServiceImpl implements SeanceService {

	@Autowired private SeanceRepository repo;
	@Autowired private AssisterRepository assisterRepo;
	@Autowired private ClientService clientService;
	@Autowired private FilmService filmService;

	
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
				
				//Restriction d'âge:
				int ageLimite = seance.getFilm().getAgeLimite();
				
				if(LocalDate.now().compareTo(c.getNaissance()) > ageLimite) {
					
					//Restriction nb de places:
					if (placeLibre(id) > 0) {
						seance.getClients().add(res);
						this.save(seance);
						this.assisterRepo.save(res);
						
					} else {
						throw new ResponseStatusException(HttpStatus.NOT_FOUND, "la seance d'id: "+id+" n'a plus de places libres");
					}
						
				} else {
					throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Le client d'id: "+cid+" est trop jeune. L'âge limite du film est de "+ageLimite+" ans.");
				}

			} else {
				throw new ResponseStatusException(HttpStatus.NOT_FOUND, "le client d'id: "+cid+" n'existe pas");
			}
			
		} else { 
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "la séance d'id: "+id+" n'existe pas");
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
		if (LocalDate.now().compareTo(c.getNaissance()) < 10)
			resultPrix -= 4;
		if (c.isEtudiant())
			resultPrix -= 2;
		
		return resultPrix;
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
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "la séance d'id: "+id+" n'existe pas");
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
			} else {
				throw new ResponseStatusException(HttpStatus.NOT_FOUND, "la séance d'id: "+id+" n'a pas de salle");
			}
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "la séance d'id: "+id+" n'existe pas");
		}
		return pl;
	}
	
	//Trouver des séances dans une plage horaire:
	
	@Override
	public List<Seance> findAllByDateBetween(LocalDateTime min, LocalDateTime max){
		return this.repo.findAllByDateBetween(min, max);
	}
	
	//Trouver des séances en fonction du genre du film:
	
	@Override
	public List<Seance> seanceByFilmGenre(String genre) {
        List<Film> film = this.filmService.findAllByGenre(genre);
        List<Seance> seances = new ArrayList<>();
        for (Film f : film) {
        	seances.addAll(this.repo.findAllByFilmId(f.getId()));
        }
        return seances;
    }
	
	//Trouver des séances en fonction de la restriction d'age:
	
	@Override
	public List<Seance> seanceByFilmAgeLimite(int ageLimite) {
        List<Film> film = this.filmService.findAllByAgeLimite(ageLimite);
        List<Seance> seances = new ArrayList<>();
        for (Film f : film) {
        	seances.addAll(this.repo.findAllByFilmId(f.getId()));
        }
        return seances;
    }
	
	//Trouver des séances en fonction du type de séance:
	
	@Override
	public List<Seance> findAllByType(String type){
		return this.repo.findAllByType(type);
	}
}
