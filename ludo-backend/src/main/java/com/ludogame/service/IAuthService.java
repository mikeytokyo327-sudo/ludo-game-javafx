package com.ludogame.service;

import com.ludogame.dto.JwtResponse;
import com.ludogame.dto.LoginRequest;
import com.ludogame.dto.RegisterRequest;

/**
 * Authentication Service Interface
 * Demonstrates Abstraction and Polymorphism principles
 */
public interface IAuthService {
    JwtResponse login(LoginRequest loginRequest);
    JwtResponse register(RegisterRequest registerRequest);
    void logout();
}