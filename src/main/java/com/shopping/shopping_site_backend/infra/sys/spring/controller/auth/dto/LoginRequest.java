package com.shopping.shopping_site_backend.infra.sys.spring.controller.auth.dto;

import lombok.Data;

@Data
public class LoginRequest {

    private String username;
    private String password;
}
