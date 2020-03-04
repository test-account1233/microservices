package com.freedom.noteservice.security;

import com.freedom.noteservice.entities.User;
import com.freedom.noteservice.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserServiceImpl userService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> byEmail = Optional.ofNullable(userService.findByEmail(email));
        if (!byEmail.isPresent()) {
            throw new UsernameNotFoundException("User does not exists");
        }
        return new SpringUser(byEmail.get());
    }
}
