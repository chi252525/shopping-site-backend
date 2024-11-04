package com.shopping.shopping_site_backend.infra.sys.spring.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

public class CustomAuthenticationFilter extends OncePerRequestFilter {

  @Override
  protected void doFilterInternal(
      HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
      throws ServletException, IOException {
    // 獲取身份驗證信息
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

    // 在這裡添加你的身份驗證邏輯
    if (authentication == null) {
      // 未經身份驗證的請求邏輯，例如返回401
      response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
      return;
    }

    // 繼續過濾鏈
    filterChain.doFilter(request, response);
  }
}
