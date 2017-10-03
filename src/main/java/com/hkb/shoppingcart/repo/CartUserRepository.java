package com.hkb.shoppingcart.repo;

import java.util.List;

import com.hkb.shoppingcart.model.CartUser;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CartUserRepository extends MongoRepository<CartUser, String> {

    public CartUser findByFirstName(String firstName);
    public CartUser findByUserName(String userName);
    public CartUser findByEmail(String email);
    public List<CartUser> findByLastName(String lastName);

}
