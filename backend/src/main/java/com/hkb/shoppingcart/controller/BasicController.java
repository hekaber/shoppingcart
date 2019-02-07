package com.hkb.shoppingcart.controller;

import com.hkb.shoppingcart.frontend.FrontendSourcesProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;

@RequestMapping
@Controller
@RequiredArgsConstructor
public class BasicController {

    private final FrontendSourcesProvider frontendSourcesProvider;

    @GetMapping("/login")
    String serveLoginPage(Model model) {
        model.addAttribute("mainScript", frontendSourcesProvider.getMainScript());

        return "page";
    }

    @GetMapping("/index")
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
