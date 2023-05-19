package com.microservice.reactive.springreactivemongo.config;

import com.microservice.reactive.springreactivemongo.RouterHandlers;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class ReactiveConfig {

  @Bean
  public RouterFunction<ServerResponse> routerFunction(RouterHandlers routerHandlers) {
    return RouterFunctions.route(
            RequestPredicates.GET("react/employee/all"), routerHandlers::getAll)
        .andRoute(RequestPredicates.GET("/react/employee/{id}"), routerHandlers::getById)
        .andRoute(RequestPredicates.GET("/react/employee/{id}/events"), routerHandlers::getEvents);
  }
}
