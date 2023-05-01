package com.microservice.authserver.service;

import com.microservice.authserver.entity.UserCredential;
import com.microservice.authserver.repository.UserCredentialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
  @Autowired private UserCredentialRepository repository;

  @Autowired private PasswordEncoder passwordEncoder;

  @Autowired private JwtService jwtService;

  public String saveUser(UserCredential user) {
    user.setPassword(passwordEncoder.encode(user.getPassword()));
    repository.save(user);
    return "User Added in the system";
  }

  public String generateToken(String username) {
    return jwtService.generateToken(username);
  }

  public void validateToken(String token) {
    jwtService.validateToken(token);
  }
}
