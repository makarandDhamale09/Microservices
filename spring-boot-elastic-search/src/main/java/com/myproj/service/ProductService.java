package com.myproj.service;

import com.myproj.entity.Product;
import com.myproj.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository repository;

    public Iterable<Product> getAllProducts(){
        return repository.findAll();
    }

    public Product insertProduct(Product product){
        return repository.save(product);
    }

}
