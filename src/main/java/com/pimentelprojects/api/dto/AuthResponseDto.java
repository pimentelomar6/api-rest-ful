package com.pimentelprojects.api.dto;

import lombok.Data;

@Data
public class AuthResponseDto {
    private String token;
    private String type = "Bearer ";

    public AuthResponseDto(String token) {
        this.token = token;
    }
}
