package com.hkb.shoppingcart.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class ProductStockException extends RuntimeException {
    public ProductStockException(String cartId, String productId) {
        super("Could not add product '" + productId + "' to cart '"+ cartId+"', no more in stocks.");
    }

}
