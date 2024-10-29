package com.shopping.shopping_site_backend.infra.sys.spring.controller.auth;

import com.shopping.shopping_site_backend.infra.sys.spring.controller.auth.dto.AuthResponse;
import com.shopping.shopping_site_backend.infra.sys.spring.controller.auth.dto.LoginRequest;
import com.shopping.shopping_site_backend.infra.sys.spring.service.JwtService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public AuthController(AuthenticationManager authenticationManager, JwtService jwtService) {
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword())
            );

            // 這裡可以生成 JWT 或其他方式返回 token
            String token = jwtService.generateToken(authentication);

            return ResponseEntity.ok(new AuthResponse(token)); // 返回成功響應
        } catch (AuthenticationException e) {
            return ResponseEntity.status(401).body("Invalid username or password."); // 返回失敗響應
        }
    }

}
