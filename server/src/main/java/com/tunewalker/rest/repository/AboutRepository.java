package com.tunewalker.rest.repository;

import com.tunewalker.rest.model.About;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AboutRepository extends MongoRepository<About, String> {
}
