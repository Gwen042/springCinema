package com.semifir.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.semifir.models.Assister;

public interface AssisterRepository extends MongoRepository<Assister, String> {
	
}
