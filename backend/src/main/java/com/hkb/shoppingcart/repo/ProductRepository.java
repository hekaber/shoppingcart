package com.hkb.shoppingcart.repo;

import com.hkb.shoppingcart.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ProductRepository extends MongoRepository<Product, String> {
    public List<Product> findByProductCode(String productCode);
    public List<Product> findByProductName(String productName);
    public List<Product> findAll();
}
