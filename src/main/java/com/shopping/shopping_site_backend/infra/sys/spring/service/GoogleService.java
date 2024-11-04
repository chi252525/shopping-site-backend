package com.shopping.shopping_site_backend.infra.sys.spring.service;

import com.shopping.shopping_site_backend.infra.dataprovider.entity.member.GoogleUser;
import com.shopping.shopping_site_backend.infra.dataprovider.entity.member.Shopper;
import com.shopping.shopping_site_backend.infra.sys.spring.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
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
  private final MemberRepository memberRepository;

  @Autowired
  public GoogleService(RestTemplate restTemplate, MemberRepository memberRepository) {
    this.restTemplate = restTemplate;
    this.memberRepository = memberRepository;
  }

  public GoogleUser getUserInfo(String code) {
    // 使用授權碼交換訪問令牌
    String token = exchangeCodeForToken(code);

    // 使用訪問令牌獲取用戶資料
    return fetchUserProfile(token);
  }

  private String exchangeCodeForToken(String code) {
    String tokenUrl = "https://oauth2.googleapis.com/token";

    // 創建請求體
    String requestBody =
        String.format(
            "code=%s&client_id=%s&client_secret=%s&redirect_uri=%s&grant_type=authorization_code",
            code, clientId, clientSecret, redirectUri);

    // 發送 POST 請求
    ResponseEntity<TokenResponse> response =
        restTemplate.postForEntity(
            tokenUrl, new HttpEntity<>(requestBody, createHeaders()), TokenResponse.class);

    if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
      return response.getBody().getAccessToken();
    } else {
      throw new RuntimeException("Failed to exchange code for token");
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
    Shopper user = memberRepository.findByEmail(email); // 假設有這個方法

    if (user != null) {
      return new GoogleUser(user.getId(), user.getName(), user.getEmail());
    } else {
      // 根據需要可以選擇拋出異常或返回 null
      throw new RuntimeException("User not found");
    }
  }
}
