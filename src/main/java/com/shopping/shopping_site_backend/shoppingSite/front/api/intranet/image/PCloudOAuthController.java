package com.shopping.shopping_site_backend.shoppingSite.front.api.intranet.image;

import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/oauth/pcloud")
public class PCloudOAuthController {
  @Value("${pcloud.client_id}")
  private String clientId;

  @Value("${pcloud.client_secret}")
  private String clientSecret;

  @Value("${pcloud.redirect_uri}")
  private String redirectUri;

  @Value("${pcloud.token_uri}")
  private String tokenUri;


  @PostMapping("/callback")
  public ResponseEntity<String> getAccessToken(@RequestParam("code") String code) {
    // 構建請求的參數
    Map<String, String> body = new HashMap<>();
    body.put("grant_type", "authorization_code");
    body.put("client_id", clientId);
    body.put("client_secret", clientSecret);
    body.put("code", code);
    body.put("redirect_uri", redirectUri);

    // 使用 RestTemplate 發送 POST 請求
    RestTemplate restTemplate = new RestTemplate();
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
    HttpEntity<Map<String, String>> entity = new HttpEntity<>(body, headers);

    ResponseEntity<String> response = restTemplate.exchange(tokenUri, HttpMethod.POST, entity, String.class);

    // 返回 access_token（可以將其儲存在 session 或數據庫中）
    return ResponseEntity.ok("Access Token Response: " + response.getBody());
  }}
