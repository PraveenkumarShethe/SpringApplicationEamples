package com.example.StatelessAuthenticationApplication.security;

import com.example.StatelessAuthenticationApplication.model.MyUser;
import com.example.StatelessAuthenticationApplication.repository.MyUserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Praveenkumar on 5/12/2021.
 */
public class StatelessLoginFilter extends AbstractAuthenticationProcessingFilter {

    private static final Logger logger = LoggerFactory.getLogger(StatelessLoginFilter.class);

    @Autowired
    private TokenAuthenticationService tokenAuthenticationService;

    @Autowired
    private MyUserRepository upayogakartaRepository;

    @Autowired
    private MyUserDetailService myUserDetailService;

    public StatelessLoginFilter(
            String defaultFilterProcessesUrl,
            TokenAuthenticationService tokenAuthenticationService,
            MyUserDetailService myUserDetailService,
            AuthenticationManager authenticationManager) {
        super(new AntPathRequestMatcher(defaultFilterProcessesUrl));
        logger.info("StatelessLoginFilter");
        this.myUserDetailService = myUserDetailService;
        this.tokenAuthenticationService = tokenAuthenticationService;
        setAuthenticationManager(authenticationManager);
    }

    public StatelessLoginFilter(String urlMapping,
                                TokenAuthenticationService tokenAuthenticationService,
                                AuthenticationManager authManager) {
        super(new AntPathRequestMatcher(urlMapping));
        logger.info(" StatelessLoginFilter ");
        this.tokenAuthenticationService = tokenAuthenticationService;
        setAuthenticationManager(authManager);
    }
    protected StatelessLoginFilter(RequestMatcher requiresAuthenticationRequestMatcher) {
        super(requiresAuthenticationRequestMatcher);
    }
    @Override
    public Authentication attemptAuthentication(
            HttpServletRequest request,
            HttpServletResponse response)
            throws AuthenticationException, IOException, ServletException {
        logger.info(" attemptAuthentication ");

        final MyUser user = new ObjectMapper().readValue(request.getInputStream(), MyUser.class);
        final UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                user.getUsername(), user.getPassword());

        return getAuthenticationManager().authenticate(usernamePasswordAuthenticationToken);
    }

    //	@SuppressWarnings("serial")
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
                                            Authentication authentication) throws IOException, ServletException {
        logger.info(" successfulAuthentication ");
        final MyUser user = myUserDetailService.getMyUser(myUserDetailService.loadUserByUsername(authentication.getName()));
        final UserAuthentication userAuthentication = new UserAuthentication(user);
        tokenAuthenticationService.addAuthenticationTokenInHeader(response, userAuthentication);
        SecurityContextHolder.getContext().setAuthentication(userAuthentication);
    }

    protected StatelessLoginFilter(RequestMatcher requiresAuthenticationRequestMatcher, TokenAuthenticationService tokenAuthenticationService) {
        super(requiresAuthenticationRequestMatcher);
        this.tokenAuthenticationService = tokenAuthenticationService;
    }
}
