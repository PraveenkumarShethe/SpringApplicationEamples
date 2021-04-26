package com.example.StatelessAuthenticationApplication.controller;

import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Praveenkumar on 4/26/2021.
 */
@RestController
public class DemoController {

    public String greetings(){
        return "Hello, Pal !!!";
    }

}
