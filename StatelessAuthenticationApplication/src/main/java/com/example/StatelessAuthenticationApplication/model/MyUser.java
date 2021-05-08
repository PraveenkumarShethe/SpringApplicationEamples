package com.example.StatelessAuthenticationApplication.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.sql.ConnectionBuilder;
import java.util.Collection;

/**
 * Created by Praveenkumar on 5/8/2021.
 */
public class MyUser extends AbstractEntity implements UserDetails {

    private static final long serialVersionUID = 2396654715019746670L;

    String username;
    String password;

    public static MyUser withDefaultPasswordEncoder() {
        return null;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }

    public ConnectionBuilder username(String user) {
        return null;
    }
}
