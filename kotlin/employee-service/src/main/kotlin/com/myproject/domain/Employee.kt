package com.myproject.domain

import jakarta.persistence.Entity
import jakarta.persistence.Table
import java.util.Date
import java.util.UUID


@Entity
@Table
class Employee(
    val firstName: String,
    val lastName: String,
    var salary: Long = 0,
    var department: String = "",
    val dateOfBirth: Date = Date(),
    val id: UUID = UUID.randomUUID()
)
