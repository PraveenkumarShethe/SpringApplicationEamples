package com.example.StatelessAuthenticationApplication.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;

/**
 * Created by Praveenkumar on 5/12/2021.
 */
public class UserAuthenticationException extends AuthenticationException {

    private static final Logger logger = LoggerFactory.getLogger(UserAuthenticationException.class);

    /**
     * Constructs an {@code AuthenticationException} with the specified message and root
     * cause.
     *
     * @param message the detail message
     * @param throwable   the root cause
     */
    public UserAuthenticationException(String message, Throwable throwable) {
        super(message, throwable);
        logException(message);
    }

    /**
     * Constructs an {@code AuthenticationException} with the specified message and no
     * root cause.
     *
     * @param msg the detail message
     */
    public UserAuthenticationException(String msg) {
        super(msg);
        logException(msg);
    }

    private static void logException(final String logMessage) {
        logger.info(logMessage);
    }

}
