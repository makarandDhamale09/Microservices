package com.microservice.reactive.springreactivemongo.controller;

import com.microservice.reactive.springreactivemongo.model.Employee;
import com.microservice.reactive.springreactivemongo.model.EmployeeEvents;
import com.microservice.reactive.springreactivemongo.repository.EmployeeRepository;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;

import java.time.Duration;
import java.util.Date;
import java.util.stream.Stream;

@RestController
@RequestMapping("/employee")
public class EmployeeResource {

  private final EmployeeRepository employeeRepository;

  public EmployeeResource(EmployeeRepository employeeRepository) {
    this.employeeRepository = employeeRepository;
  }

  @GetMapping("/all")
  public Flux<Employee> getAllEmployee() {
    return employeeRepository.findAll();
  }

  @GetMapping("/{id}")
  public Mono<Employee> getId(@PathVariable("id") final String id) {
    return employeeRepository.findById(id);
  }

  @GetMapping(value = "/{id}/events" , produces = MediaType.TEXT_EVENT_STREAM_VALUE)
  public Flux<EmployeeEvents> getEvents(@PathVariable("id") final String id) {
    return employeeRepository
        .findById(id)
        .flatMapMany(
            employee -> {
              Flux<Long> interval = Flux.interval(Duration.ofSeconds(2));

              Flux<EmployeeEvents> employeeEventsFlux =
                  Flux.fromStream(Stream.generate(() -> new EmployeeEvents(employee, new Date())));

              return Flux.zip(interval, employeeEventsFlux).map(Tuple2::getT2);
            });
  }
}
