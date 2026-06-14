package com.ludogame.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * User Model (Frontend)
 * Demonstrates Encapsulation with private fields and getters/setters
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private Long id;
    private String username;
    private String email;
    private Boolean enabled;
}