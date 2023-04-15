package com.tunewalker.rest.repository;

import com.tunewalker.rest.model.AdminUser;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface AdminUserRepository extends MongoRepository<AdminUser, String > {
    Optional<AdminUser> findAdminUserByUsername(String name);
}
