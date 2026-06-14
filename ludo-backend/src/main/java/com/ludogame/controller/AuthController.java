package com.ludogame.controller;

import com.ludogame.dto.JwtResponse;
import com.ludogame.dto.LoginRequest;
import com.ludogame.dto.RegisterRequest;
import com.ludogame.service.IAuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Authentication Controller
 * REST endpoints for user authentication
 */
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@Slf4j
@CrossOrigin(origins = "*", maxAge = 3600)
public class AuthController {

    private final IAuthService authService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequest loginRequest) {
        log.info("Login request for email: {}", loginRequest.getEmail());
        try {
            JwtResponse jwtResponse = authService.login(loginRequest);
            return ResponseEntity.ok(jwtResponse);
        } catch (Exception e) {
            log.error("Login failed: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new ErrorResponse("Invalid email or password"));
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody RegisterRequest registerRequest) {
        log.info("Registration request for email: {}", registerRequest.getEmail());
        try {
            JwtResponse jwtResponse = authService.register(registerRequest);
            return ResponseEntity.status(HttpStatus.CREATED).body(jwtResponse);
        } catch (Exception e) {
            log.error("Registration failed: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ErrorResponse(e.getMessage()));
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout() {
        authService.logout();
        return ResponseEntity.ok(new MessageResponse("Logged out successfully"));
    }

    @GetMapping("/health")
    public ResponseEntity<?> health() {
        return ResponseEntity.ok(new MessageResponse("API is running"));
    }

    public static class ErrorResponse {
        public String error;
        public ErrorResponse(String error) {
            this.error = error;
        }
    }

    public static class MessageResponse {
        public String message;
        public MessageResponse(String message) {
            this.message = message;
        }
    }
}