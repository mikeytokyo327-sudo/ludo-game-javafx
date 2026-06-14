package com.ludogame;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Main application class for Ludo Game Backend
 * Demonstrates Abstraction principle by using Spring Boot's abstraction layer
 */
@SpringBootApplication
public class LudoBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(LudoBackendApplication.class, args);
    }

    /**
     * Bean for password encoding
     * Demonstrates Encapsulation and DI principle
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}