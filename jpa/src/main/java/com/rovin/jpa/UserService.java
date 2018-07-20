package com.rovin.jpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserDao userRepository;
    public User findUserByName(String username) {
        return userRepository.findByUsername(username);
    }
}
