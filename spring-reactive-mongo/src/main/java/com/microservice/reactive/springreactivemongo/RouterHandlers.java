package com.microservice.reactive.springreactivemongo;

import com.microservice.reactive.springreactivemongo.model.Employee;
import com.microservice.reactive.springreactivemongo.model.EmployeeEvents;
import com.microservice.reactive.springreactivemongo.repository.EmployeeRepository;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;

import java.time.Duration;
import java.util.Date;
import java.util.stream.Stream;

@Component
public class RouterHandlers {

  private final EmployeeRepository employeeRepository;

  public RouterHandlers(EmployeeRepository employeeRepository) {
    this.employeeRepository = employeeRepository;
  }

  public Mono<ServerResponse> getAll(ServerRequest request) {
    return ServerResponse.ok().body(employeeRepository.findAll(), Employee.class);
  }

  public Mono<ServerResponse> getById(ServerRequest request) {
    return ServerResponse.ok()
        .body(employeeRepository.findById(request.pathVariable("id")), Employee.class);
  }

  public Mono<ServerResponse> getEvents(ServerRequest request){
    return ServerResponse.ok()
            .contentType(MediaType.TEXT_EVENT_STREAM)
            .body(employeeRepository
                    .findById(request.pathVariable("id"))
                    .flatMapMany(
                            employee -> {
                              Flux<Long> interval = Flux.interval(Duration.ofSeconds(2));

                              Flux<EmployeeEvents> employeeEventsFlux =
                                      Flux.fromStream(Stream.generate(() -> new EmployeeEvents(employee, new Date())));

                              return Flux.zip(interval, employeeEventsFlux).map(Tuple2::getT2);
                            }), EmployeeEvents.class);
  }
}
