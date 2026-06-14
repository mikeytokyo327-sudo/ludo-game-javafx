package com.ludogame.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * JWT Response DTO
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class JwtResponse {

    private String accessToken;
    private String type = "Bearer";
    private Long id;
    private String username;
    private String email;

    public JwtResponse(String accessToken, Long id, String username, String email) {
        this.accessToken = accessToken;
        this.id = id;
        this.username = username;
        this.email = email;
    }
}