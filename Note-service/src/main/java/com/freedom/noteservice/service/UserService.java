package com.freedom.noteservice.service;

import com.freedom.noteservice.entities.User;

public interface UserService {
    User findByEmail(String email);

    long findIdByEmail(String email);
}
