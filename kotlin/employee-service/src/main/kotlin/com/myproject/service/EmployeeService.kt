package com.myproject.service

import com.myproject.domain.Employee
import com.myproject.dto.EmployeeDto
import org.springframework.stereotype.Service

@Service
class EmployeeService {

    private final val employees = mutableListOf<Employee>()

    init {
        employees.add(Employee("John", "Doe", 2000, "IT"))
        employees.add(Employee("Alex", "Richard", 5000, "HR"))
    }

    fun getAllEmployees(): List<EmployeeDto> {

        val employeeDtoList = employees.map {
            EmployeeDto(
                it.firstName, it.lastName,
                it.dateOfBirth, it.salary, it.department
            )
        }
        return employeeDtoList
    }
}