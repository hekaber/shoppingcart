package com.hkb.shoppingcart.controller;

import com.hkb.shoppingcart.exceptions.ShoppingCartNotFoundException;
import com.hkb.shoppingcart.model.ShoppingCart;
import com.hkb.shoppingcart.repo.ShoppingCartRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/carts")
public class ShoppingCartRestController {

    private final ShoppingCartRepository shoppingCartRepository;

    private static final Logger logger = LoggerFactory.getLogger(ShoppingCartRestController.class);

    @Autowired
    ShoppingCartRestController(ShoppingCartRepository shoppingCartRepository){

        this.shoppingCartRepository = shoppingCartRepository;
    }

    @RequestMapping(method = RequestMethod.GET)
    List<ShoppingCart> getShoppingCarts(){
        logger.info("---Getting all products---");
        return this.shoppingCartRepository.findAll();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/user/{userName}")
    List<ShoppingCart> getShoppingCartsByUserName(@PathVariable String userName) {
        logger.info("---Getting all products for user '"+ userName +"'---");
        return this.shoppingCartRepository.findByUserName(userName);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{cartId}")
    ShoppingCart readShoppingCart(@PathVariable String cartId){
        logger.debug("---Reading shopping cart '" + cartId +"'---");
        ShoppingCart cart = this.shoppingCartRepository.findOne(cartId);

        if(cart != null){
            return cart;
        }
        else {
            throw new ShoppingCartNotFoundException(cartId);
        }
    }

    @RequestMapping(method = RequestMethod.POST)
    ResponseEntity<?> add(@RequestBody ShoppingCart input){
        ShoppingCart result = this.shoppingCartRepository.save(
                new ShoppingCart(
                    input.status,
                    input.userName,
                    input.products,
                    input.orderDate,
                    input.lastModified
                ));
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(result.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

//    @RequestMapping("/carts")
//    public String index() {
//        return "Shopping cart backend.";
//    }

}
