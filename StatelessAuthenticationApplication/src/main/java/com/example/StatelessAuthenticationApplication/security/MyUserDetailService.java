package com.example.StatelessAuthenticationApplication.security;

import com.example.StatelessAuthenticationApplication.repository.MyUserRepository;
import com.example.StatelessAuthenticationApplication.model.MyUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * Created by Praveenkumar on 5/12/2021.
 */
@Service
public class MyUserDetailService implements UserDetailsService {

    @Autowired
    private MyUserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user;
        final MyUser upayogakarta = userRepository.findByUsername(username);
        if (upayogakarta != null) {
            user = new User(
                    upayogakarta.getUsername(),
                    upayogakarta.getPassword(),
                    upayogakarta.isEnabled(),
                    upayogakarta.isAccountNonExpired(),
                    upayogakarta.isCredentialsNonExpired(),
                    upayogakarta.isAccountNonLocked(),
                    upayogakarta.getAuthorities()
            );
        } else {
            throw new UserAuthenticationException("This Upayogakarta was not found");
        }

        return user;
    }

    public MyUser getMyUser(User user) {
        return userRepository.findByUsername(user.getUsername());
    }

    public MyUser getMyUser(UserDetails principal) {
        return getMyUser(
                (User) loadUserByUsername(principal.getUsername()));
    }
}
