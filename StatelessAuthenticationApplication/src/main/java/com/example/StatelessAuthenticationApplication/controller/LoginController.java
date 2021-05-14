/** 
 * 
 */
package com.example.StatelessAuthenticationApplication.controller;


import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.StatelessAuthenticationApplication.model.MyUser;
import com.example.StatelessAuthenticationApplication.security.MyUserDetailService;
import com.example.StatelessAuthenticationApplication.security.TokenAuthenticationService;
import com.example.StatelessAuthenticationApplication.security.UserAuthentication;
import com.example.StatelessAuthenticationApplication.security.UserAuthenticationException;

/**
 * @author: Praveenkumar
 * Created date: May 14, 2021
 */
@RestController
@RequestMapping(value = "/login", produces = MediaType.APPLICATION_JSON_VALUE)
public class LoginController {
	
	@Autowired
    private MyUserDetailService userDetailService;

    @Autowired
    private TokenAuthenticationService tokenAuthenticationService;
	
	@RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    @Transactional(Transactional.TxType.NEVER)
    public void authenticate(@RequestBody MyUser user,
                             BindingResult bindingResult,
                             HttpServletResponse httpServletResponse) {

        UserDetails userDetails = userDetailService.loadUserByUsername(user.getUsername());
        MyUser upayogakarta = userDetailService.getMyUser(userDetails);
        if (upayogakarta == null) {
            throw new UserAuthenticationException("Upayogakarta not found");
        }
        tokenAuthenticationService.addAuthenticationTokenInHeader(httpServletResponse
                , new UserAuthentication(upayogakarta));
    }

}
