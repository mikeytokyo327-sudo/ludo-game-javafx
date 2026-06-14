package com.ludogame.service;

import com.ludogame.dto.JwtResponse;
import com.ludogame.dto.LoginRequest;
import com.ludogame.dto.RegisterRequest;
import com.ludogame.entity.Role;
import com.ludogame.entity.User;
import com.ludogame.repository.RoleRepository;
import com.ludogame.repository.UserRepository;
import com.ludogame.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.validation.Valid;

/**
 * Authentication Service Implementation
 * Demonstrates Polymorphism (implements IAuthService)
 * Demonstrates Encapsulation and business logic separation
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class AuthService implements IAuthService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    @Override
    public JwtResponse login(@Valid LoginRequest loginRequest) {
        log.info("User login attempt for email: {}", loginRequest.getEmail());

        User user = userRepository.findByEmail(loginRequest.getEmail())
                .orElseThrow(() -> new RuntimeException("Invalid email or password"));

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        user.getUsername(),
                        loginRequest.getPassword()
                )
        );

        String jwtToken = jwtTokenProvider.generateToken(authentication);

        log.info("User successfully logged in: {}", user.getUsername());
        return new JwtResponse(jwtToken, user.getId(), user.getUsername(), user.getEmail());
    }

    @Override
    public JwtResponse register(@Valid RegisterRequest registerRequest) {
        log.info("User registration attempt for email: {}", registerRequest.getEmail());

        if (!registerRequest.getPassword().equals(registerRequest.getConfirmPassword())) {
            throw new RuntimeException("Passwords do not match");
        }

        if (userRepository.existsByEmail(registerRequest.getEmail())) {
            throw new RuntimeException("Email already registered");
        }

        if (userRepository.existsByUsername(registerRequest.getUsername())) {
            throw new RuntimeException("Username already taken");
        }

        User user = new User(
                registerRequest.getUsername(),
                registerRequest.getEmail(),
                passwordEncoder.encode(registerRequest.getPassword())
        );

        Role userRole = roleRepository.findByName("ROLE_USER")
                .orElseThrow(() -> new RuntimeException("Role not found"));
        user.addRole(userRole);

        User savedUser = userRepository.save(user);
        log.info("User successfully registered: {}", savedUser.getUsername());

        Authentication authentication = new UsernamePasswordAuthenticationToken(
                savedUser.getUsername(),
                registerRequest.getPassword()
        );
        String jwtToken = jwtTokenProvider.generateToken(authentication);

        return new JwtResponse(jwtToken, savedUser.getId(), savedUser.getUsername(), savedUser.getEmail());
    }

    @Override
    public void logout() {
        log.info("User logged out");
    }
}