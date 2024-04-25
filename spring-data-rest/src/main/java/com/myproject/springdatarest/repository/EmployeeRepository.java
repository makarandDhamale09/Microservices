package com.myproject.springdatarest.repository;

import com.myproject.springdatarest.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
}
