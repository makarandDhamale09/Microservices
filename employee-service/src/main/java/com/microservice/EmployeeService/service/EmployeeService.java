package com.microservice.EmployeeService.service;

import com.microservice.EmployeeService.model.Employee;
import com.microservice.EmployeeService.repository.EmployeeRepository;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class EmployeeService {

  @Autowired EmployeeRepository employeeRepository;

  public String addEmployee(Employee employee) {
    List<Employee> employeeList = getAllEmployees();
    Employee savedEmployee;
    if (!employeeList.contains(employee)) {
      savedEmployee = employeeRepository.save(employee);
    } else {
      return "Employee Already Present";
    }
    return savedEmployee.getId();
  }

  public List<Employee> getAllEmployees() {
    log.info("Fetching all employees");
    return employeeRepository.findAll();
  }

  public List<Employee> getEmployeesByDept(String department) {
    return employeeRepository.findAllByDepartment(department);
  }
}
