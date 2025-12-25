package com.myproject.user.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/message")
public class MessageController {

  @GetMapping
  public String sendMessage() {
    return "This is a test message from MessageController!";
  }
}
