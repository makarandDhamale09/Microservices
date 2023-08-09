package com.myproject.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UuidGenerator;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "PAYMENT_INFO")
public class PaymentInfo {
  @Id @UuidGenerator private String paymentId;
  private String accountNo;
  private double amount;
  private String cardType;
  private Long passengerId;
}
