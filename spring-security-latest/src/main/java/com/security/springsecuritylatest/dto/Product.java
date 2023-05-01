package com.security.springsecuritylatest.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class Product {
  private int productId;
  private String name;
  private int qty;
  private int price;
}
