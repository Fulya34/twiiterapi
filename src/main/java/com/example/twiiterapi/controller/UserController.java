package com.example.twiiterapi.controller;

import com.example.twiiterapi.dto.RegisterRequest;
import com.example.twiiterapi.dto.LoginRequest;
import com.example.twiiterapi.dto.UserResponse;
import com.example.twiiterapi.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    // Register endpoint
    @PostMapping("/register")
    public ResponseEntity<UserResponse> register(@RequestBody RegisterRequest request) {
        UserResponse savedUser = userService.register(request); // artık DTO ile çalışıyor
        return ResponseEntity.status(201).body(savedUser);
    }

    // Login endpoint
    @PostMapping("/login")
    public ResponseEntity<UserResponse> login(@RequestBody LoginRequest request) {
        UserResponse loggedUser = userService.login(request);
        return ResponseEntity.ok(loggedUser);
    }
}