package com.microservice.authserver.repository;

import com.microservice.authserver.entity.UserCredential;
import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserCredentialRepository extends MongoRepository<UserCredential, Integer> {
  Optional<UserCredential> findByName(String name);
}
