package com.example.ppmtool.services;

import com.example.ppmtool.domain.User;
import com.example.ppmtool.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public User saveUser (User newUser) {
        newUser.setPassword(bCryptPasswordEncoder.encode(newUser.getPassword()));
        // Username has to be unique (exception)
        // Make sure the password and confirm password match
        // We don't persist or show the confirm password
        return userRepository.save(newUser);
    }
}
