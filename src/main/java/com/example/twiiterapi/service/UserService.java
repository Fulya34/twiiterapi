package com.example.twiiterapi.service;

import com.example.twiiterapi.dto.RegisterRequest;
import com.example.twiiterapi.dto.LoginRequest;
import com.example.twiiterapi.dto.UserResponse;
import com.example.twiiterapi.model.User;
import jakarta.validation.Valid;

import java.util.Optional;

public interface UserService {

    UserResponse register(RegisterRequest request);

    UserResponse login(LoginRequest request);

    Optional<User> findById(Long id);

    Optional<User> findByEmail(String email);

    User save( User user);
}