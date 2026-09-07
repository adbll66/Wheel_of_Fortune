package com.adylbek.wheel_of_fortune.controller;

import com.adylbek.wheel_of_fortune.dto.LoginRequest;
import com.adylbek.wheel_of_fortune.dto.RegisterRequest;
import com.adylbek.wheel_of_fortune.entity.User;
import com.adylbek.wheel_of_fortune.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody RegisterRequest request) {
        User registeredUser = authService.register(request);
        return ResponseEntity.ok(registeredUser);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest request) {
        String tokenOrMessage = authService.login(request);
        return ResponseEntity.ok(tokenOrMessage);
    }
}