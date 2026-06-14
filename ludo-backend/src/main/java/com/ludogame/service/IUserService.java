package com.ludogame.service;

import com.ludogame.dto.UserDTO;

import java.util.List;

/**
 * User Service Interface
 * Demonstrates Abstraction principle
 */
public interface IUserService {
    UserDTO getUserById(Long id);
    List<UserDTO> getAllUsers();
    UserDTO updateUser(Long id, UserDTO userDTO);
    void deleteUser(Long id);
    UserDTO getUserByEmail(String email);
}