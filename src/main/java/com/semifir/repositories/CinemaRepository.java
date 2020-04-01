package com.semifir.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.semifir.models.Cinema;

public interface CinemaRepository extends MongoRepository<Cinema, String> {

}
