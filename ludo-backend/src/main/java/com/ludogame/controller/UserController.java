package com.ludogame.controller;

import com.ludogame.dto.UserDTO;
import com.ludogame.service.IUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * User Controller
 * REST endpoints for user management
 */
@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
@Slf4j
@CrossOrigin(origins = "*", maxAge = 3600)
public class UserController {

    private final IUserService userService;

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        log.info("Fetching all users");
        List<UserDTO> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Long id) {
        log.info("Fetching user with id: {}", id);
        UserDTO user = userService.getUserById(id);
        return ResponseEntity.ok(user);
    }

    @GetMapping("/email/{email}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<UserDTO> getUserByEmail(@PathVariable String email) {
        log.info("Fetching user with email: {}", email);
        UserDTO user = userService.getUserByEmail(email);
        return ResponseEntity.ok(user);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<UserDTO> updateUser(@PathVariable Long id, @RequestBody UserDTO userDTO) {
        log.info("Updating user with id: {}", id);
        UserDTO updatedUser = userService.updateUser(id, userDTO);
        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        log.info("Deleting user with id: {}", id);
        userService.deleteUser(id);
        return ResponseEntity.ok(new MessageResponse("User deleted successfully"));
    }

    public static class MessageResponse {
        public String message;
        public MessageResponse(String message) {
            this.message = message;
        }
    }
}