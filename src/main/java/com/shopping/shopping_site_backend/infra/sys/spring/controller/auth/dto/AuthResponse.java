package com.shopping.shopping_site_backend.infra.sys.spring.controller.auth.dto;

import lombok.Data;

@Data
public class AuthResponse {
    private String token;

    public AuthResponse(String token) {
        this.token = token;
    }
}
