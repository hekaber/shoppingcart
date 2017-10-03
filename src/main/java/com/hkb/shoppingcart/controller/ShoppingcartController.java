package com.hkb.shoppingcart.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
public class ShoppingcartController {

    @RequestMapping("/")
    public String index() {
        return "Shopping cart backend.";
    }

}
