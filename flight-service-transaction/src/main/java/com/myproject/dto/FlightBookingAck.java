package com.myproject.dto;

import com.myproject.entity.PassengerInfo;

public record FlightBookingAck(
    String status, double totalFare, String pnrNo, PassengerInfo passengerInfo) {}
