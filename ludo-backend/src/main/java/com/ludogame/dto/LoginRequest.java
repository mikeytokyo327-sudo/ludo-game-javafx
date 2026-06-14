package com.ludogame.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

/**
 * Login Request DTO
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequest {

    @NotBlank(message = "Email cannot be blank")
    @Email
    private String email;

    @NotBlank(message = "Password cannot be blank")
    private String password;
}