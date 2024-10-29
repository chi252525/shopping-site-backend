package com.shopping.shopping_site_backend.infra.sys.spring.controller;

import com.shopping.shopping_site_backend.infra.dataprovider.entity.member.GoogleUser;
import com.shopping.shopping_site_backend.infra.sys.spring.service.UserService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/api/user")
    public GoogleUser getUser(@AuthenticationPrincipal OAuth2User principal) {
        return userService.getUserByOAuth2User(principal); // 使用服务获取用户信息
    }
}
