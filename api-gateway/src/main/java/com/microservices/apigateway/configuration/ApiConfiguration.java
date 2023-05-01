package com.microservices.apigateway.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ApiConfiguration {

  // Will be doing the routing via the application.yml file
  /*@Bean
  public RouteLocator gatewayRouter(RouteLocatorBuilder builder) {
    return builder
        .routes()
        .route(
            p ->
                p.path("/get")
                    .filters(
                        f ->
                            f.addRequestHeader("name", "myName")
                                .addRequestParameter("param", "myParam"))
                    .uri("http://httpbin.org:80"))
        .route(p -> p.path("/employee-service/**").uri("lb://employee-service"))
        .build();
  }*/

    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
