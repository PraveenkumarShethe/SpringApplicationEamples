package com.example.StatelessAuthenticationApplication.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.xml.bind.DatatypeConverter;

/**
 * Created by Praveenkumar on 5/11/2021.
 */
@Service
public class TokenAuthenticationService {

    private static final String AUTH_HEADER_NAME = "X-AUTH-TOKEN";
    private static final long TEN_DAYS = 1000 * 60 * 60 * 24 * 10;

    private final TokenHandler tokenHandler;

    @Autowired
    public TokenAuthenticationService(@Value("${token.secret}") String secret) {
        tokenHandler = new TokenHandler(DatatypeConverter.parseBase64Binary(secret));
    }

    public void addAuthenticationTokenInHeader(HttpServletResponse response, UpayogakartaAuthentication authentication) {
        final Upayogakarta user = (Upayogakarta) authentication.getDetails();
        user.setExpires(System.currentTimeMillis() + TEN_DAYS);
        response.addHeader(AUTH_HEADER_NAME, tokenHandler.createTokenForUser(user));
    }

    public Authentication getAuthenticationFromHeader(HttpServletRequest request) {
        final String token = request.getHeader(AUTH_HEADER_NAME);
        if (token != null) {
            final Upayogakarta user = tokenHandler.parseUserFromToken(token);
            if (user != null) {
                return new UserAuthentication(user);
            }
        }
        return null;
    }
}