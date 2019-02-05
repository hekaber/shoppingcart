package com.hkb.shoppingcart.controller;

import com.hkb.shoppingcart.exceptions.UserEmailExistsException;
import com.hkb.shoppingcart.exceptions.UserNotFoundException;
import com.hkb.shoppingcart.exceptions.UserNameExistsException;
import com.hkb.shoppingcart.model.CartUser;
import com.hkb.shoppingcart.repo.CartUserRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/users")
public class CartUserRestController {

    private final CartUserRepository cartUserRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private static final Logger logger = LoggerFactory.getLogger(CartUserRestController.class);

    @Autowired
    CartUserRestController(CartUserRepository cartUserRepository, BCryptPasswordEncoder bCryptPasswordEncoder){

        this.cartUserRepository = cartUserRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;

    }

    @PostMapping("/signup")
    ResponseEntity<?> signUp(@RequestBody CartUser cartUser) {
        cartUser.setPassword(bCryptPasswordEncoder.encode(cartUser.getPassword()));
        if(cartUserRepository.findByUserName(cartUser.getUserName()) != null){
            throw new UserNameExistsException(cartUser.getUserName());
        }
        else if(cartUserRepository.findByEmail(cartUser.email) != null){
            throw new UserEmailExistsException(cartUser.email);
        }
        else {
            CartUser newUser = cartUserRepository.save(cartUser);
            return ResponseEntity.ok("User '" + newUser.getUserName() +"' created.");
        }
    }

    @RequestMapping(method = RequestMethod.GET)
    List<CartUser> getUsers(){
        logger.debug("---Getting all users---");
        return this.cartUserRepository.findAll();
    }

    @RequestMapping(method = RequestMethod.POST)
    ResponseEntity<?> add(@RequestBody CartUser input){
        logger.debug("---Adding new user '" + input.lastName +" " + input.firstName +"'---");
        CartUser result = this.cartUserRepository.save(
                new CartUser(
                        input.firstName,
                        input.lastName,
                        input.getUserName(),
                        input.getPassword(),
                        input.email
                ));

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(result.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{userId}")
    CartUser readUser(@PathVariable String userId){
        logger.debug("---Reading cartUser '" + userId + "'---");
        CartUser cartUser = this.cartUserRepository.findOne(userId);

        if (cartUser != null){
            return cartUser;
        }
        else {
            throw new UserNotFoundException(userId);
        }
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{userId}")
    ResponseEntity<?> delete(@PathVariable String userId){
        if(this.cartUserRepository.exists(userId)){
            logger.debug("---Deleting user '"+ userId + "'---");
            return ResponseEntity.ok("CartUser '" + userId + "' deleted.");
        }
        else {
            throw new UserNotFoundException(userId);
        }
    }
}
