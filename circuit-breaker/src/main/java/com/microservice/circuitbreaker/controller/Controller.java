package com.microservice.circuitbreaker.controller;

import com.microservice.circuitbreaker.dto.Activity;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class Controller {

  private static final Logger log = LoggerFactory.getLogger(Controller.class);

  private static final String BOARED_API = "https://www.boredapi.com/api/activity";

  private RestTemplate restTemplate;

  public Controller(RestTemplate restTemplate) {
    this.restTemplate = restTemplate;
  }

  @GetMapping("/hello")
  public String hello() {
    return "Hello World!!";
  }

  @GetMapping("/activity")
  @CircuitBreaker(name = "randomActivity" , fallbackMethod = "fallbackRandomActivity")
  public String getRandomActivity() {
    ResponseEntity<Activity> responseEntity = restTemplate.getForEntity(BOARED_API, Activity.class);
    Activity activity = responseEntity.getBody();
    log.info("Activity received : "+ activity.activity());
    return activity.activity();
  }

  public String fallbackRandomActivity(Throwable throwable){
    return "The Random activity api is down";
  }
}
