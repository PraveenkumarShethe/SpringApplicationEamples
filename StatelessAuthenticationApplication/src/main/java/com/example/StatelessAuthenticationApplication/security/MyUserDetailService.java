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
        final MyUser myUser = userRepository.findByUsername(username);
        if (myUser != null) {
            user = new User(
                    myUser.getUsername(),
                    myUser.getPassword(),
                    myUser.isEnabled(),
                    myUser.isAccountNonExpired(),
                    myUser.isCredentialsNonExpired(),
                    myUser.isAccountNonLocked(),
                    myUser.getAuthorities()
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
