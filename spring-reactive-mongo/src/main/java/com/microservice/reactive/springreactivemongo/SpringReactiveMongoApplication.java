package com.microservice.reactive.springreactivemongo;

import com.microservice.reactive.springreactivemongo.model.Employee;
import com.microservice.reactive.springreactivemongo.repository.EmployeeRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.UUID;
import java.util.stream.Stream;

@SpringBootApplication
public class SpringReactiveMongoApplication {

  public static void main(String[] args) {
    SpringApplication.run(SpringReactiveMongoApplication.class, args);
  }

  @Bean
  public CommandLineRunner employees(EmployeeRepository employeeRepository) {
    return args -> {
      employeeRepository
          .deleteAll()
          .subscribe(
              null,
              null,
              () -> {
                Stream.of(
                        new Employee(UUID.randomUUID().toString(), "Makarand", 1000L),
                        new Employee(UUID.randomUUID().toString(), "Peter", 2000L),
                        new Employee(UUID.randomUUID().toString(), "Sam", 3000L),
                        new Employee(UUID.randomUUID().toString(), "Chris", 4000L))
                    .forEach(
                        emp -> {
                          employeeRepository.save(emp).subscribe(System.out::println);
                        });
              });
    };
  }
}
