package com.shopping.shopping_site_backend.infra.sys.spring.service;

import com.shopping.shopping_site_backend.infra.dataprovider.entity.shopper.GoogleUser;
import com.shopping.shopping_site_backend.infra.dataprovider.entity.shopper.Shopper;
import com.shopping.shopping_site_backend.infra.sys.spring.repository.ShopperRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class GoogleService {

  @Value("${spring.security.oauth2.client.registration.google.client-id}")
  private String clientId;

  @Value("${spring.security.oauth2.client.registration.google.client-secret}")
  private String clientSecret;

  @Value("${spring.security.oauth2.client.registration.google.redirect-uri}")
  private String redirectUri;

  private final RestTemplate restTemplate;
  private final ShopperRepository shopperRepository;

  @Autowired
  public GoogleService(RestTemplate restTemplate, ShopperRepository shopperRepository) {
    this.restTemplate = restTemplate;
    this.shopperRepository = shopperRepository;
  }

  public GoogleUser getUserInfo(String code) {
    // 使用授權碼交換訪問令牌
    String token = exchangeCodeForToken(code);

    // 使用訪問令牌獲取用戶資料
    return fetchUserProfile(token);
  }

  private String exchangeCodeForToken(String code) {
    String tokenUrl = "https://oauth2.googleapis.com/token";

    // Create the request body
    String requestBody = String.format(
        "code=%s&client_id=%s&client_secret=%s&redirect_uri=%s&grant_type=authorization_code",
        code, clientId, clientSecret, redirectUri);

    // Set headers for the POST request
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

    // Send POST request
    HttpEntity<String> entity = new HttpEntity<>(requestBody, headers);
    ResponseEntity<TokenResponse> response = restTemplate.postForEntity(
        tokenUrl, entity, TokenResponse.class);



    // Check for successful response and extract token
    if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
      return response.getBody().getAccessToken();
    } else {
      String errorMessage = "Failed to exchange code for token. Status: " + response.getStatusCode() +
          ", Response: " + response.getBody();
      throw new RuntimeException(errorMessage);
    }
  }


  private GoogleUser fetchUserProfile(String accessToken) {
    String userInfoUrl = "https://www.googleapis.com/oauth2/v2/userinfo";

    HttpHeaders headers = new HttpHeaders();
    headers.set("Authorization", "Bearer " + accessToken);

    // 發送 GET 請求以獲取用戶資料
    ResponseEntity<GoogleUser> response =
        restTemplate.exchange(
            userInfoUrl, HttpMethod.GET, new HttpEntity<>(headers), GoogleUser.class);

    if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
      return response.getBody();
    } else {
      throw new RuntimeException("Failed to fetch user profile");
    }
  }

  private HttpHeaders createHeaders() {
    HttpHeaders headers = new HttpHeaders();
    headers.set("Content-Type", "application/x-www-form-urlencoded");
    return headers;
  }

  public GoogleUser getUserByEmail(String email) {
    Shopper user = shopperRepository.findByEmail(email); // 假設有這個方法

    if (user != null) {
      return new GoogleUser(user.getId(), user.getName(), user.getEmail());
    } else {
      // 根據需要可以選擇拋出異常或返回 null
      throw new RuntimeException("User not found");
    }
  }
}
