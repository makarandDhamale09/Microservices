package com.myproject.dto;

import com.myproject.entity.PassengerInfo;
import com.myproject.entity.PaymentInfo;

public record FlightBookingRequest(PassengerInfo passengerInfo, PaymentInfo paymentInfo) {}
