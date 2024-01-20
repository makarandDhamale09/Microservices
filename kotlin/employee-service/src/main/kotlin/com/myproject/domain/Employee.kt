package com.myproject.domain

import java.util.Date
import java.util.UUID

class Employee(
    val firstName: String,
    val lastName: String,
    var salary: Long = 0,
    var department: String = "",
    val dateOfBirth: Date = Date(),
    val id: UUID = UUID.randomUUID()
)
