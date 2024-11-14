package com.shopping.shopping_site_backend.infra.sys.spring.controller.auth;

import com.shopping.shopping_site_backend.infra.dataprovider.entity.shopper.GoogleUser;
import com.shopping.shopping_site_backend.infra.sys.spring.service.GoogleService;
import com.shopping.shopping_site_backend.infra.sys.spring.service.JwtService;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;
import org.springframework.web.util.UriComponentsBuilder;
@Slf4j
@RestController
@RequestMapping("/api")
public class AuthController {
  @Value("${spring.security.oauth2.client.registration.google.client-id}")
  private String clientId;

  @Value("${spring.security.oauth2.client.registration.google.client-secret}")
  private String clientSecret;

  @Value("${spring.security.oauth2.client.registration.google.redirect-uri}")
  private String redirectUri;

  private final GoogleService googleService;

  private final JwtService jwtService;
  private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

  public AuthController(
      GoogleService googleService,
      AuthenticationManager authenticationManager,
      JwtService jwtService) {
    this.googleService = googleService;
    this.jwtService = jwtService;
  }

  @GetMapping("/oauth2/login")
  public ResponseEntity<?> login() {
    try {
      // 使用 UriComponentsBuilder 自動處理編碼
      URI redirectUrl =
          UriComponentsBuilder.fromUriString("https://accounts.google.com/o/oauth2/v2/auth")
              .queryParam("client_id", clientId)
              .queryParam("redirect_uri", redirectUri)
              .queryParam("response_type", "code")
              .queryParam("scope", "profile email")
              .build()
              .encode() // 自動編碼 URI 中的特殊字符
              .toUri();

      return ResponseEntity.status(HttpStatus.FOUND).location(redirectUrl).build();

    } catch (Exception e) {
      // 處理任何異常（如 UnsupportedEncodingException 或 URISyntaxException）
      e.printStackTrace();
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("URL 構建錯誤");
    }
  }

  @GetMapping("/oauth2/callback")
  public RedirectView googleCallback(
      @RequestParam String code) {
    logger.info("Received OAuth2 callback with code: {}", code);
    // 處理 code 取得 access token，驗證後端邏輯
    // 使用 code 交換 Google 的訪問令牌和用戶資料
    System.out.println("OAuth2 callback received with code: " + code );

    GoogleUser user;
    try {
      user = googleService.getUserInfo(code);
      logger.info("Successfully retrieved user info: {}", user);
    } catch (Exception e) {
      logger.error("Error retrieving user info: {}", e.getMessage());
      RedirectView redirectView = new RedirectView();
      redirectView.setUrl("http://localhost:9000/");  // 將 URL 設定為 Vue 應用的根路徑
      return redirectView;   }

    // 檢查用戶是否存在，並根據需要創建或更新用戶資料
    // 生成 JWT token
    String token = jwtService.generateToken(user);
    Map<String, String> response = new HashMap<>();
    response.put("status", "success");
    response.put("message", "Login successful");
    response.put("token", token); // 返回 JWT 或 session ID
    RedirectView redirectView = new RedirectView();
    redirectView.setUrl("http://localhost:9000/?token=" + token); // 將 URL 設定為 Vue 應用的根路徑
    return redirectView;
  }

  @GetMapping("/user/profile")
  public ResponseEntity<?> getUserProfile(@RequestHeader("Authorization") String token) {
    String email = jwtService.extractUsername(token.replace("Bearer ", ""));
    GoogleUser user = googleService.getUserByEmail(email); // 假設有這個方法
    return ResponseEntity.ok(user);
  }

  //    客戶端移除 token
  //    @PostMapping("/logout")
  //    public ResponseEntity<?> logout() {
  //        return ResponseEntity.ok();
  //    }

}
