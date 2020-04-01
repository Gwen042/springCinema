package com.semifir.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.semifir.models.Salle;

public interface SalleRepository extends MongoRepository<Salle, String> {

}
