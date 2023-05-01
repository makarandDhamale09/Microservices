package com.security.springsecuritylatest.service;

import com.security.springsecuritylatest.dto.Product;
import jakarta.annotation.PostConstruct;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

  List<Product> productList = null;

  @PostConstruct
  public void loadProductsFromDb() throws NoSuchAlgorithmException {
    Random random = SecureRandom.getInstanceStrong();
    productList =
        IntStream.range(1, 100)
            .mapToObj(
                i ->
                    Product.builder()
                        .productId(i)
                        .name("product" + i)
                        .qty(random.nextInt(10))
                        .price(random.nextInt(5000))
                        .build())
            .toList();
  }

  public List<Product> getAllProducts() {
    return productList;
  }

  public Product getProductById(int id) {
    return productList.stream()
        .filter(p -> p.getProductId() == id)
        .findAny()
        .orElseThrow(() -> new RuntimeException("product " + id + " not found"));
  }
}
