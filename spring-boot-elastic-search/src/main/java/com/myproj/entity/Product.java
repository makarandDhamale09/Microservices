package com.myproj.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.elasticsearch.annotations.Document;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Document(indexName = "product_index")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Product {
    private int id;
    private String productName;
    private int qty;
    private double price;
}
