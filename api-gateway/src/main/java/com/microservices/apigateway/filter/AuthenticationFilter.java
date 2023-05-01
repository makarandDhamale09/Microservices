package com.microservices.apigateway.filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.web.client.RestTemplate;

public class AuthenticationFilter
    extends AbstractGatewayFilterFactory<AuthenticationFilter.Config> {

  @Autowired private RouteValidator validator;

  @Autowired private RestTemplate restTemplate;

  public AuthenticationFilter() {
    super(Config.class);
  }

  @Override
  public GatewayFilter apply(Config config) {
    return (((exchange, chain) -> {
      if (validator.isSecured.test(exchange.getRequest())) {
        if (!exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
          throw new RuntimeException("Missing Authorisation error");
        }
        String authHeader =
            exchange.getRequest().getHeaders().get(HttpHeaders.AUTHORIZATION).get(0);

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
          authHeader = authHeader.substring(7);
        }
        try {
          restTemplate.getForObject(
              "http://AUTH_SERVER/auth/validate?token=" + authHeader, String.class);

        } catch (Exception e) {
          System.out.println("invalid access.....");
          throw new RuntimeException("unauthorized access to application");
        }
      }
      return chain.filter(exchange);
    }));
  }

  public static class Config {}
}
