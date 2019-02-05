package com.hkb.shoppingcart.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class UserNameExistsException extends RuntimeException  {
    public UserNameExistsException(String userName){
        super("An account with the username '" + userName + "' already exists");
    }
}

