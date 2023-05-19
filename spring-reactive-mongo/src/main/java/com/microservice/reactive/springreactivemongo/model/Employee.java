package com.microservice.reactive.springreactivemongo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public record Employee(@Id String id, String employeeName, Long salary) {
}
