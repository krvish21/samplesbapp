package com.krvish.samplesbapp.auth.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.krvish.samplesbapp.auth.dto.LoginRequest;
import com.krvish.samplesbapp.auth.dto.LoginResponse;
import com.krvish.samplesbapp.auth.dto.RegisterRequest;
import com.krvish.samplesbapp.auth.service.AuthService;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public void register(@RequestBody @Valid RegisterRequest request) {
        authService.register(request.getEmail(), request.getPassword());
    }

    @PostMapping("/login")
    public LoginResponse login(@RequestBody @Valid LoginRequest request) {
        String token = authService.login(request.getEmail(), request.getPassword());
        return new LoginResponse(token);
    }

}
