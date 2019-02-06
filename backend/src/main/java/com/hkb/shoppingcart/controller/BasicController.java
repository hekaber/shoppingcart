package com.hkb.shoppingcart.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;

@RequestMapping
@Controller
public class BasicController {

    @GetMapping("/")
    String sayHello() {
        return "page";
    }

    @GetMapping(value="/index")
    public void method(HttpServletResponse response){
        response.setHeader(HttpHeaders.LOCATION, "/");
        response.setStatus(HttpStatus.FOUND.value());
    }
//second way of doing redirection
//    @GetMapping(value="/index")
//    public ModelAndView method(){
//        return new ModelAndView("redirect:" + "/");
//    }


}
