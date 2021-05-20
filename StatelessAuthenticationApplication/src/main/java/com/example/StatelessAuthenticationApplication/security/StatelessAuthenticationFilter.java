package com.example.StatelessAuthenticationApplication.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Created by Praveenkumar on 5/11/2021.
 */
public class StatelessAuthenticationFilter extends GenericFilterBean {

    private final TokenAuthenticationService tokenAuthenticationService;

    private static final Logger logger = LoggerFactory.getLogger(StatelessAuthenticationFilter.class);

    public StatelessAuthenticationFilter(TokenAuthenticationService taService) {
        logger.info("StatelessAuthenticationFilter constructor ");
        this.tokenAuthenticationService = taService;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        logger.info("doFilter method");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        SecurityContextHolder.getContext()
                .setAuthentication(tokenAuthenticationService.getAuthenticationFromHeader((HttpServletRequest) request));
        chain.doFilter(request, response);
    }
}
