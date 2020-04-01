package com.semifir.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.semifir.models.Seance;

public interface SeanceRepository extends MongoRepository<Seance, String> {


}
