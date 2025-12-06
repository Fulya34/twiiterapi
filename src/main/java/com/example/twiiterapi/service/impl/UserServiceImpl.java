package com.example.twiiterapi.service.impl;

import com.example.twiiterapi.dto.RegisterRequest;
import com.example.twiiterapi.dto.LoginRequest;
import com.example.twiiterapi.dto.UserResponse;
import com.example.twiiterapi.model.User;
import com.example.twiiterapi.repository.UserRepository;
import com.example.twiiterapi.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public UserResponse register(RegisterRequest request) {
        // register logic
        return null;
    }

    @Override
    public UserResponse login(LoginRequest request) {
        // login logic
        return null;
    }

    @Override
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }
}