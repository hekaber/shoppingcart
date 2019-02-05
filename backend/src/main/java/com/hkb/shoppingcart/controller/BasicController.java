package com.hkb.shoppingcart.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class BasicController {

    @RequestMapping
    @GetMapping("/")
    String sayHello() {
        return "page";
    }

}
