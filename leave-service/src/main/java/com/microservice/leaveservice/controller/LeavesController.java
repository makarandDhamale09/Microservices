package com.microservice.leaveservice.controller;

import com.microservice.leaveservice.feign.EmployeeFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/leaves")
public class LeavesController {

    @Autowired
    EmployeeFeignClient feignClient;

    @GetMapping("/employee-hello")
    public String helloController() {
        return feignClient.getHello();
    }

    @GetMapping("hello")
    public String hello() {
        return "Hello from leaves service";
    }

}
