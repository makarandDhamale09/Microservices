package com.myProject.redis.repository;

import com.myProject.redis.entity.Product;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductDao {

    public static final String HASK_KEY = "Product";
    private final RedisTemplate redisTemplate;

    public ProductDao(RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public Product save(Product product){
        redisTemplate.opsForHash().put(HASK_KEY, product.getId(), product);
        return product;
    }

    public List<Product> findAll(){
        return redisTemplate.opsForHash().values(HASK_KEY);
    }

    public Product findProductById(int id){
        return (Product) redisTemplate.opsForHash().get(HASK_KEY,id);
    }

    public String deleteProduct(int id){
        redisTemplate.opsForHash().delete(HASK_KEY,id);
        return "Product Removed";
    }
}
