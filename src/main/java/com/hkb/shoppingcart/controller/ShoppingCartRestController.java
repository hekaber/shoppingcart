package com.hkb.shoppingcart.controller;

import com.hkb.shoppingcart.exceptions.ShoppingCartListNotFoundException;
import com.hkb.shoppingcart.exceptions.ShoppingCartNotFoundException;
import com.hkb.shoppingcart.model.ShoppingCart;
import com.hkb.shoppingcart.repo.ShoppingCartRepository;
import com.hkb.shoppingcart.utils.CustomErrorType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriComponentsBuilder;

import javax.xml.ws.Response;
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
    ResponseEntity<List<ShoppingCart>> getShoppingCarts(){
        logger.info("---Getting all shopping carts---");
        List<ShoppingCart> carts = this.shoppingCartRepository.findAll();

        if(carts.isEmpty()){
            logger.error("---Did not find any shopping carts---");
            throw new ShoppingCartListNotFoundException();
        }
        return new ResponseEntity<List<ShoppingCart>>(carts, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/user/{userName}")
    ResponseEntity<List<ShoppingCart>> getShoppingCartsByUserName(@PathVariable String userName) {
        logger.info("---Getting all shopping carts for user '"+ userName +"'---");
        List<ShoppingCart> carts = this.shoppingCartRepository.findByUserName(userName);

        if(carts.isEmpty()){
            logger.error("---Did not find shopping carts for user '"+ userName +"'---");
            throw new ShoppingCartListNotFoundException(userName);
        }
        return new ResponseEntity<List<ShoppingCart>>(carts, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{cartId}")
    ResponseEntity<?> readShoppingCart(@PathVariable String cartId){
        logger.info("---Reading shopping cart '" + cartId +"'---");
        ShoppingCart cart = this.shoppingCartRepository.findOne(cartId);

        if(cart == null){
            logger.error("---Unable to read shopping cart '" + cartId +"' not found---");
            throw new ShoppingCartNotFoundException(cartId);
        }

        return new ResponseEntity<ShoppingCart>(cart, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    ResponseEntity<?> add(@RequestBody ShoppingCart input, UriComponentsBuilder ucBuilder){
        ShoppingCart result = this.shoppingCartRepository.save(
                new ShoppingCart(
                    input.status,
                    input.userName,
                    input.products,
                    input.productQuantities,
                    input.orderDate,
                    input.lastModified
                ));

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/carts/{id}").buildAndExpand(result.getId()).toUri());
        return new ResponseEntity<ShoppingCart>(result, headers, HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{cartId}")
    ResponseEntity<?> delete(@PathVariable String cartId){
        logger.info("---Deleting shopping cart '" + cartId +"'---");
        ShoppingCart cart = this.shoppingCartRepository.findOne(cartId);

        if(cart != null){
            this.shoppingCartRepository.delete(cartId);
            return new ResponseEntity<ShoppingCart>(HttpStatus.NO_CONTENT);
        }
        else {
            logger.error("---Unable to delete shopping cart '" + cartId +"' not found---");
            throw new ShoppingCartNotFoundException(cartId);
        }
    }


}
