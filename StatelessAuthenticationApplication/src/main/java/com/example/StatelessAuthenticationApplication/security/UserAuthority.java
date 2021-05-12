package com.example.StatelessAuthenticationApplication.security;

import com.example.StatelessAuthenticationApplication.model.Role;
import org.springframework.security.core.GrantedAuthority;

/**
 * Created by Praveenkumar on 5/12/2021.
 */
public class UserAuthority implements GrantedAuthority {

    private Role role;

    public UserAuthority(Role role) {
        this.role = role;
    }

    @Override
    public String getAuthority() {
        return role.getName();
    }
}
