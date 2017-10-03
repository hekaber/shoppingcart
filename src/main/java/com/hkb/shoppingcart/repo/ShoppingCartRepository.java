package com.hkb.shoppingcart.repo;

import com.hkb.shoppingcart.model.ShoppingCart;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ShoppingCartRepository extends MongoRepository<ShoppingCart, String> {
    public List<ShoppingCart> findByUserName(String userName);
}
