package com.tunewalker.rest.repository;

import com.tunewalker.rest.model.RefreshToken;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface RefreshTokenRepository extends MongoRepository<RefreshToken, String> {

    Optional<RefreshToken> findRefreshTokenByToken(String s);
}
