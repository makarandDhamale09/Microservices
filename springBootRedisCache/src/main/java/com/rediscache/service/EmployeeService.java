package com.rediscache.service;

import com.rediscache.domain.Employee;
import com.rediscache.exception.ResourceNotFoundException;
import com.rediscache.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    private final EmployeeRepository repository;

    public EmployeeService(EmployeeRepository repository) {
        this.repository = repository;
    }

    public List<Employee> getAllEmployees() {
        return repository.findAll();
    }

    public Employee getEmployee(String id) {
        System.out.println("fetching employee: "+ id+ " from db");
        return repository.findById(Integer.valueOf(id))
                .orElseThrow(() -> new ResourceNotFoundException("Employee with id :" + id + " not found"));
    }
}
