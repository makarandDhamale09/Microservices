package com.myproject.service;

import com.myproject.dto.FlightBookingAck;
import com.myproject.dto.FlightBookingRequest;
import com.myproject.entity.*;
import com.myproject.repository.PassengerInfoRepository;
import com.myproject.repository.PaymentInfoRepository;
import com.myproject.utils.PaymentUtils;
import java.util.UUID;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class FlightBookingService {

  private final PassengerInfoRepository passengerInfoRepository;
  private final PaymentInfoRepository paymentInfoRepository;

  public FlightBookingService(
      PassengerInfoRepository passengerInfoRepository,
      PaymentInfoRepository paymentInfoRepository) {
    this.passengerInfoRepository = passengerInfoRepository;
    this.paymentInfoRepository = paymentInfoRepository;
  }

  @Transactional
  public FlightBookingAck bookFlightTicket(FlightBookingRequest request) {
    PassengerInfo passengerInfo = request.passengerInfo();
    passengerInfo = passengerInfoRepository.save(passengerInfo);

    PaymentInfo paymentInfo = request.paymentInfo();

    PaymentUtils.validateCreditLimit(paymentInfo.getAccountNo(), passengerInfo.getFare());

    paymentInfo.setPassengerId(passengerInfo.getPId());
    paymentInfo.setAmount(passengerInfo.getFare());
    paymentInfoRepository.save(paymentInfo);

    return new FlightBookingAck(
        "SUCCESS",
        passengerInfo.getFare(),
        UUID.randomUUID().toString().split("-")[0],
        passengerInfo);
  }
}
