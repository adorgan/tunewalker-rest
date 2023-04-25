package com.tunewalker.rest.repository;

import com.tunewalker.rest.model.Subscriber;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface SubscriberRepository extends MongoRepository<Subscriber, String> {
    Subscriber findByEmail(String email);
}
