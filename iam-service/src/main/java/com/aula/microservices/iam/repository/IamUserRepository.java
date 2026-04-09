package com.aula.microservices.iam.repository;

import com.aula.microservices.iam.entity.IamUser;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IamUserRepository extends MongoRepository<IamUser, String> {
    
    Optional<IamUser> findByUsername(String username);
    
    Optional<IamUser> findByEmail(String email);
    
    boolean existsByUsername(String username);
    
    boolean existsByEmail(String email);
}