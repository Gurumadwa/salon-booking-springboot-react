package com.koushik.salonservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class HomeController {

    @GetMapping
    public String HomeControllerHandler(){
        return "Hi, from Salon service";
    }

}