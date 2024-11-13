package com.shopping.shopping_site_backend.infra.sys.spring.service;

import lombok.Data;

@Data
public class TokenResponse {
  private String refresh_token;
  private String scope;
  private String access_token;
  private String token_type;
  private String expires_in;
}
