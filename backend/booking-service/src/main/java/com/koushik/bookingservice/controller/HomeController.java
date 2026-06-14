package com.koushik.bookingservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping("/booking-service-test")
    public String TestMethod(){
        return "Hello from booking service";
    }
}
