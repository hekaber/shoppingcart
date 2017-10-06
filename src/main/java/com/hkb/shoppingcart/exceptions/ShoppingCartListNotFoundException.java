package com.hkb.shoppingcart.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ShoppingCartListNotFoundException extends RuntimeException {
    public ShoppingCartListNotFoundException() {
        super("Could not find any shopping cart.");
    }
    public ShoppingCartListNotFoundException(String userName) {
        super("Could not find shopping cart list for user'" + userName + "'.");
    }
}
