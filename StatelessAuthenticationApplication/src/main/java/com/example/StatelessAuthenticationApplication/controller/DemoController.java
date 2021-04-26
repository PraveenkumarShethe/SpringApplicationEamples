package com.example.StatelessAuthenticationApplication.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Praveenkumar on 4/26/2021.
 */
@RestController
@RequestMapping(value = "/demo", produces = MediaType.APPLICATION_JSON_VALUE)
public class DemoController {

    @RequestMapping(method = RequestMethod.GET, value = "/greetings", produces = MediaType.APPLICATION_JSON_VALUE)
    public String greetings(){
        return "Hello, Pal !!!";
    }

}
