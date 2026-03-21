package com.koushik.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

        @GetMapping
        public String HomeControllerHandler(){
            return "Hi, from salon booking user service";
        }

}
