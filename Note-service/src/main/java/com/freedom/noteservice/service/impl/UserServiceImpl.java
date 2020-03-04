package com.freedom.noteservice.service.impl;

import com.freedom.noteservice.entities.User;
import com.freedom.noteservice.repository.UserRepository;
import com.freedom.noteservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public long findIdByEmail(String email) {
        return userRepository.findUserIdByEmail(email);
    }
}
