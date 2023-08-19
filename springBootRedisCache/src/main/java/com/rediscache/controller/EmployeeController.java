package com.rediscache.controller;

import com.rediscache.domain.Employee;
import com.rediscache.exception.ResourceNotFoundException;
import com.rediscache.service.EmployeeService;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class EmployeeController {

    private final EmployeeService service;

    public EmployeeController(EmployeeService service) {
        this.service = service;
    }

    @GetMapping("/hello")
    public String helloWorld() {
        return "Hello World";
    }

    @GetMapping("/employee")
    public List<Employee> getAllEmployees() {
        return service.getAllEmployees();
    }

    @GetMapping("/employee/{id}")
    @Cacheable(value = "employees", key = "#id")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable String id) {
        try {
            return ResponseEntity.ok(service.getEmployee(id));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
