package com.aula.microservices.users.repository;

import com.aula.microservices.users.entity.UserProfile;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<UserProfile, String> {
    boolean existsByEmail(String email);
}