package com.microservice.reactive.springreactivemongo.repository;

import com.microservice.reactive.springreactivemongo.model.Employee;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface EmployeeRepository extends ReactiveMongoRepository<Employee, String> {
}
