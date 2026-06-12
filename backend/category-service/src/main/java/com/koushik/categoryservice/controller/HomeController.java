package com.koushik.categoryservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping("/test-category")
    public String TestMethod(){
        return "Hello from category service";
    }
}
