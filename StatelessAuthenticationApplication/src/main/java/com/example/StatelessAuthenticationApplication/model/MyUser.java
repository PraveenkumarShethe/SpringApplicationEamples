package com.example.StatelessAuthenticationApplication.model;

import com.example.StatelessAuthenticationApplication.security.UserAuthority;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.security.core.CredentialsContainer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import jdk.nashorn.internal.ir.annotations.Ignore;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by Praveenkumar on 5/8/2021.
 */
public class MyUser extends AbstractEntity implements UserDetails, CredentialsContainer {

    private static final long serialVersionUID = 2396654715019746670L;
    @Ignore
    @Column(name = "first_name")
    private String firstName;

    @Ignore
    @Column(name = "last_name")
    private String lastName;

    @Ignore
    @Column(name = "email")
    private String email;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Ignore
    @Column(name = "expires")
    private long expires;

    @Ignore
    @Column(name = "account_expired")
    private boolean accountExpired;

    @Ignore
    @Column(name = "account_locked")
    private boolean accountLocked;

    @Ignore
    @Column(name = "credentials_expired")
    private boolean credentialsExpired;

    @Ignore
    @Column(name = "account_enabled")
    private boolean accountEnabled;

    @Ignore
    @ManyToOne(targetEntity = Role.class)
    @JoinColumn(name = "role_id")
    private Role role;

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    @JsonIgnore
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new UserAuthority(this.role));
        return authorities;
    }

    @Override
    @JsonIgnore
    public String getPassword() {
        return password;
    }

    @JsonProperty
    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonExpired() {
        return !accountExpired;
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonLocked() {
        return !accountLocked;
    }

    @Override
    @JsonIgnore
    public boolean isCredentialsNonExpired() {
        return !credentialsExpired;
    }

    @Override
    @JsonIgnore
    public boolean isEnabled() {
        return !accountEnabled;
    }

    public long getExpires() {
        return expires;
    }

    public void setExpires(long expires) {
        this.expires = expires;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + ": " + getUsername();
    }


    @Override
    public void eraseCredentials() {
        this.password = null;
    }

}
