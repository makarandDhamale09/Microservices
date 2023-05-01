package com.microservice.EmployeeService.repository;

import com.microservice.EmployeeService.model.Employee;
import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface EmployeeRepository extends MongoRepository<Employee, String> {
  List<Employee> findAllByDepartment(String department);
}
