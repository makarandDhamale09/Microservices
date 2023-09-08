package com.myproj.controller;

import com.myproj.entity.Product;
import com.myproj.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/apis")
public class ProductController {

    private final ProductService service;

    @GetMapping("/products")
    public Iterable<Product> getAllProducts(){
        return service.getAllProducts();
    }

    @PostMapping("/add")
    public Product insertProduct(@RequestBody Product product){
        return service.insertProduct(product);
    }
}
