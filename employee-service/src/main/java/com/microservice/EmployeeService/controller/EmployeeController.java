package com.microservice.EmployeeService.controller;

import com.microservice.EmployeeService.model.Employee;
import com.microservice.EmployeeService.model.UserProperties;
import com.microservice.EmployeeService.service.EmployeeService;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

  @Autowired private EmployeeService service;

  @Autowired private UserProperties userProperties;

  @GetMapping("/hello")
  public Map<String, UserProperties.UserDetails> hello() {
    return userProperties.getUserDetails();
  }

  @GetMapping("/getAllEmployees")
  public List<Employee> getAllEmployees() {
    return service.getAllEmployees();
  }

  @PostMapping("/addEmployee")
  public String addEmployee(@RequestBody Employee employee) {
    return service.addEmployee(employee);
  }

  @GetMapping("/getEmployees")
  public List<Employee> getEmployeesInDepartment(@RequestParam String dept) {
    return service.getEmployeesByDept(dept);
  }
}
