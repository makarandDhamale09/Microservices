package com.microservice.authserver.controller;

import com.microservice.authserver.entity.UserCredential;
import com.microservice.authserver.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

  @Autowired private AuthService authService;

  @Autowired private AuthenticationManager authenticationManager;

  @PostMapping("/register")
  public String addNewUser(@RequestBody UserCredential user) {
    return authService.saveUser(user);
  }

  @PostMapping("/token")
  public String getToken(@RequestBody UserCredential user) {
    Authentication authenticate =
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(user.getName(), user.getPassword()));
    if (authenticate.isAuthenticated()) {
      return authService.generateToken(user.getName());
    } else throw new UsernameNotFoundException("User not available...!!");
  }

  @GetMapping("/validate")
  public String validateToken(@RequestParam("token") String token) {
    authService.validateToken(token);
    return "Token is valid";
  }
}
