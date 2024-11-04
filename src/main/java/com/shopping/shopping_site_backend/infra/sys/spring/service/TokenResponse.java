package com.shopping.shopping_site_backend.infra.sys.spring.service;

import lombok.Data;

@Data
public class TokenResponse {
  private String accessToken;
  private String refreshToken;
  private String scope;
  private String tokenType;
  private int expiresIn;
}
