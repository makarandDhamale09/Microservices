package com.myproject;

import com.myproject.dto.FlightBookingAck;
import com.myproject.dto.FlightBookingRequest;
import com.myproject.service.FlightBookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableTransactionManagement
@RestController
public class FlightServiceTransactionApplication {

  @Autowired
  private FlightBookingService service;

  @PostMapping("/bookFlightTicket")
  public FlightBookingAck bookFlightTicket(@RequestBody FlightBookingRequest request){
    return service.bookFlightTicket(request);
  }

  public static void main(String[] args) {
    SpringApplication.run(FlightServiceTransactionApplication.class, args);
  }
}
