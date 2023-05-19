package com.microservice.reactive.springreactivemongo.model;

import java.util.Date;

public record EmployeeEvents(Employee employee, Date date) {}
