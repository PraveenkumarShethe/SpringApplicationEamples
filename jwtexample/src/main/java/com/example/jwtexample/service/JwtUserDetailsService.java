package com.example.jwtexample.service;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 * Created by Praveenkumar on 6/12/2021.
 */
@Service
public class JwtUserDetailsService implements UserDetailsService {
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("LoadUserByUserName: " + username);
        if ("javainuse".equals(username)) {
            return new User("javainuse", "$2a$10$zzp8hoFQHxj/d/yNM04m7uZ25CoX5ckfhYCaDI1za25HIDMDkZb9u",
                    new ArrayList<>());
        } else {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
    }
}
