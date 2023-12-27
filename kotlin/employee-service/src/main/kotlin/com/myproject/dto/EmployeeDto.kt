package com.myproject.dto

import java.util.*

data class EmployeeDto(
    val firstName: String,
    val lastName: String,
    val dateOfBirth: Date,
    val salary: Long,
    val department: String
)