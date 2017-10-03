package com.hkb.shoppingcart.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class UserEmailExistsException extends RuntimeException  {
    public UserEmailExistsException(String email){
        super("An account with the email address '" + email + "' already exists");
    }
}

