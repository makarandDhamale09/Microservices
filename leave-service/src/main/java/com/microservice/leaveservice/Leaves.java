package com.microservice.leaveservice;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("Leaves")
@Getter
@Setter
public class Leaves {
    @Id
    private String id;
    private String empId;
    private int earnedLeaves;
    private int sickLeaves;
}
