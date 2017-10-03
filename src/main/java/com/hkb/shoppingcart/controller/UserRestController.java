package com.hkb.shoppingcart.controller;

import com.hkb.shoppingcart.exceptions.UserNotFoundException;
import com.hkb.shoppingcart.model.User;
import com.hkb.shoppingcart.repo.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserRestController {

    private final UserRepository userRepository;

    private static final Logger logger = LoggerFactory.getLogger(UserRestController.class);

    @Autowired
    UserRestController(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @RequestMapping(method = RequestMethod.GET)
    List<User> getUsers(){
        logger.debug("---Getting all users---");
        return this.userRepository.findAll();
    }

    @RequestMapping(method = RequestMethod.POST)
    ResponseEntity<?> add(@RequestBody User input){
        logger.debug("---Adding new user '" + input.lastName +" " + input.firstName +"'---");
        User result = this.userRepository.save(
                new User(
                        input.firstName,
                        input.lastName,
                        input.email
                ));

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(result.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{userId}")
    User readUser(@PathVariable String userId){
        logger.debug("---Reading user '" + userId + "'---");
        User user = this.userRepository.findOne(userId);

        if (user != null){
            return user;
        }
        else {
            throw new UserNotFoundException(userId);
        }
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{userId}")
    ResponseEntity<?> delete(@PathVariable String userId){
        if(this.userRepository.exists(userId)){
            logger.debug("---Deleting user '"+ userId + "'---");
            return ResponseEntity.ok("User '" + userId + "' deleted.");
        }
        else {
            throw new UserNotFoundException(userId);
        }
    }
}
