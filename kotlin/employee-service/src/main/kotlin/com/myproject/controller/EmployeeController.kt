package com.myproject.controller

import com.myproject.dto.EmployeeDto
import com.myproject.service.EmployeeService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class EmployeeController(val service : EmployeeService) {

    @GetMapping("/employees")
    fun getAllEmployees(): List<EmployeeDto>{
        return service.getAllEmployees()
    }
}