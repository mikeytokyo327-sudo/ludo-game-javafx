package com.ludogame.config;

import com.ludogame.entity.Role;
import com.ludogame.entity.User;
import com.ludogame.repository.RoleRepository;
import com.ludogame.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * Data Initializer
 * Demonstrates initialization of default roles and test users
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class DataInitializer implements CommandLineRunner {

    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        log.info("Initializing application data...");

        if (roleRepository.findByName("ROLE_USER").isEmpty()) {
            roleRepository.save(new Role("ROLE_USER", "Regular user role"));
            log.info("ROLE_USER created");
        }

        if (roleRepository.findByName("ROLE_ADMIN").isEmpty()) {
            roleRepository.save(new Role("ROLE_ADMIN", "Administrator role"));
            log.info("ROLE_ADMIN created");
        }

        if (!userRepository.existsByEmail("user1@example.com")) {
            User user1 = new User("player1", "user1@example.com",
                    passwordEncoder.encode("password123"));
            Role userRole = roleRepository.findByName("ROLE_USER")
                    .orElseThrow(() -> new RuntimeException("Role not found"));
            user1.addRole(userRole);
            userRepository.save(user1);
            log.info("Test user1 created");
        }

        if (!userRepository.existsByEmail("user2@example.com")) {
            User user2 = new User("player2", "user2@example.com",
                    passwordEncoder.encode("password123"));
            Role userRole = roleRepository.findByName("ROLE_USER")
                    .orElseThrow(() -> new RuntimeException("Role not found"));
            user2.addRole(userRole);
            userRepository.save(user2);
            log.info("Test user2 created");
        }

        if (!userRepository.existsByEmail("admin@example.com")) {
            User admin = new User("admin", "admin@example.com",
                    passwordEncoder.encode("admin123"));
            Role adminRole = roleRepository.findByName("ROLE_ADMIN")
                    .orElseThrow(() -> new RuntimeException("Role not found"));
            Role userRole = roleRepository.findByName("ROLE_USER")
                    .orElseThrow(() -> new RuntimeException("Role not found"));
            admin.addRole(adminRole);
            admin.addRole(userRole);
            userRepository.save(admin);
            log.info("Test admin user created");
        }

        log.info("Data initialization completed");
    }
}