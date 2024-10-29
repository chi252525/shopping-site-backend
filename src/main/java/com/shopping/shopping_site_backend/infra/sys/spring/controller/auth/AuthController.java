package com.shopping.shopping_site_backend.infra.sys.spring.controller.auth;

import com.shopping.shopping_site_backend.infra.dataprovider.entity.member.GoogleUser;
import com.shopping.shopping_site_backend.infra.sys.spring.controller.auth.dto.AuthResponse;
import com.shopping.shopping_site_backend.infra.sys.spring.controller.auth.dto.LoginRequest;
import com.shopping.shopping_site_backend.infra.sys.spring.service.GoogleService;
import com.shopping.shopping_site_backend.infra.sys.spring.service.JwtService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

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

    public AuthController(GoogleService googleService, AuthenticationManager authenticationManager, JwtService jwtService) {
        this.googleService = googleService;
        this.jwtService = jwtService;
    }

    @GetMapping("/oauth2/login")
    public ResponseEntity<?> login() {
        String redirectUrl = "https://accounts.google.com/o/oauth2/v2/auth?client_id=" + clientId
                + "&redirect_uri=" + redirectUri
                + "&response_type=code"
                + "&scope=profile email";

        return ResponseEntity.status(HttpStatus.FOUND).location(URI.create(redirectUrl)).build();
    }

    @GetMapping("/oauth2/callback")
    public ResponseEntity<?> googleCallback(@RequestParam String code) {
        // 使用 code 交換 Google 的訪問令牌和用戶資料
        GoogleUser user = googleService.getUserInfo(code);

        // 檢查用戶是否存在，並根據需要創建或更新用戶資料
        // 生成 JWT token
        String token = jwtService.generateToken(user);

        return ResponseEntity.ok(new AuthResponse(token));
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
