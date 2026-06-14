package com.ludogame.service;

import com.ludogame.dto.UserDTO;
import com.ludogame.entity.User;
import com.ludogame.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * User Service Implementation
 * Demonstrates Polymorphism (implements IUserService)
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class UserService implements IUserService {

    private final UserRepository userRepository;

    @Override
    public UserDTO getUserById(Long id) {
        log.info("Fetching user with id: {}", id);
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return mapToDTO(user);
    }

    @Override
    public List<UserDTO> getAllUsers() {
        log.info("Fetching all users");
        return userRepository.findAll()
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public UserDTO updateUser(Long id, UserDTO userDTO) {
        log.info("Updating user with id: {}", id);
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        user.setUsername(userDTO.getUsername());
        user.setEmail(userDTO.getEmail());

        User updatedUser = userRepository.save(user);
        return mapToDTO(updatedUser);
    }

    @Override
    @Transactional
    public void deleteUser(Long id) {
        log.info("Deleting user with id: {}", id);
        if (!userRepository.existsById(id)) {
            throw new RuntimeException("User not found");
        }
        userRepository.deleteById(id);
    }

    @Override
    public UserDTO getUserByEmail(String email) {
        log.info("Fetching user with email: {}", email);
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return mapToDTO(user);
    }

    private UserDTO mapToDTO(User user) {
        return new UserDTO(user.getId(), user.getUsername(), user.getEmail(), user.getEnabled());
    }
}