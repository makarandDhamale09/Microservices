package com.security.springsecuritylatest.controller;

import com.security.springsecuritylatest.dto.AuthRequest;
import com.security.springsecuritylatest.dto.Product;
import com.security.springsecuritylatest.entity.UserInfo;
import com.security.springsecuritylatest.service.JwtService;
import com.security.springsecuritylatest.service.ProductService;
import com.security.springsecuritylatest.service.UserInfoService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
public class ProductController {

  @Autowired private ProductService productService;

  @Autowired private UserInfoService userInfoService;

  @Autowired private JwtService jwtService;

  @Autowired private AuthenticationManager authenticationManager;

  @GetMapping("/welcome")
  public String welcome() {
    return "Welcome to Product Controller this endpoint is not secure";
  }

  @PostMapping("/authenticate")
  public String authenticateAndGetToken(@RequestBody AuthRequest authRequest) {
    Authentication authenticate =
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                authRequest.getUserName(), authRequest.getPassword()));

    if (authenticate.isAuthenticated()) {
      return jwtService.generateToken(authRequest.getUserName());
    } else {
      throw new UsernameNotFoundException("Invalid user request...!!!!");
    }
  }

  @GetMapping("/all")
  @PreAuthorize("hasAuthority('ROLE_ADMIN')")
  public List<Product> getAllProducts() {
    return productService.getAllProducts();
  }

  @GetMapping("/{id}")
  @PreAuthorize("hasAuthority('ROLE_USER')")
  public Product getProductById(@PathVariable int id) {
    return productService.getProductById(id);
  }

  @PostMapping("/newUser")
  public String addNewUser(@RequestBody UserInfo userInfo) {
    return userInfoService.addUser(userInfo);
  }
}
