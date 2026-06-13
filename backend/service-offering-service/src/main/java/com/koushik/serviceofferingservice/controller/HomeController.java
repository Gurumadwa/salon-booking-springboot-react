package com.koushik.serviceofferingservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping("/service-offering-test")
    public String home(){
        return "Hello from service-offering-service";
    }

}
