package com.ludogame.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * User DTO (Data Transfer Object)
 * Demonstrates Encapsulation for data transfer between layers
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    private Long id;

    @NotBlank(message = "Username cannot be blank")
    @Size(min = 3, max = 50)
    private String username;

    @NotBlank(message = "Email cannot be blank")
    @Email
    private String email;

    private Boolean enabled;
}