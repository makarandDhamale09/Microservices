package com.microservice.leaveservice.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(value = "employeeService" ,url="http://localhost:8081/employee-service")
public interface EmployeeFeignClient {
    @GetMapping("/hello")
    public String getHello();
}
