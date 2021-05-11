package com.example.StatelessAuthenticationApplication.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;

/**
 * Created by Praveenkumar on 5/11/2021.
 */
public class StatelessUserAuthenticationException extends AuthenticationException {

    private static final Logger logger = LoggerFactory.getLogger(StatelessUserAuthenticationException.class);

    public StatelessUserAuthenticationException(String msg, Throwable cause) {
        super(msg, cause);

    }

    public StatelessUserAuthenticationException(String msg) {
        super(msg);
    }
}
