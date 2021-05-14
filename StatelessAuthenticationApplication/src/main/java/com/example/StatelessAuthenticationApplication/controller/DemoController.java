package com.example.StatelessAuthenticationApplication.controller;

import org.apache.tomcat.util.http.parser.MediaTypeCache;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.StatelessAuthenticationApplication.model.MyUser;
import com.example.StatelessAuthenticationApplication.repository.MyUserRepository;

/**
 * Created by Praveenkumar on 4/26/2021.
 */
@RestController
@RequestMapping(value = "/demo", produces = MediaType.APPLICATION_JSON_VALUE)
public class DemoController {
	
	private MyUserRepository userRepo;

    @RequestMapping(method = RequestMethod.GET, value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public String greetings(){
        return "<h1>Hello, Pal !!!<h1>";
    }
    
    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void saveMyUser(@RequestBody MyUser myUser) {
    	userRepo.save(myUser);
    }

}
