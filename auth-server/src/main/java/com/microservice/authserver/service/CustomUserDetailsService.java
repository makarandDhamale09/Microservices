package com.microservice.authserver.service;

import com.microservice.authserver.config.CustomUserDetails;
import com.microservice.authserver.entity.UserCredential;
import com.microservice.authserver.repository.UserCredentialRepository;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class CustomUserDetailsService implements UserDetailsService {

  @Autowired
  private UserCredentialRepository userCredentialRepository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    Optional<UserCredential> userCredential = userCredentialRepository.findByName(username);
    return userCredential
        .map(CustomUserDetails::new)
        .orElseThrow(
            () -> new UsernameNotFoundException("user with username " + username + " not found"));
  }
}
