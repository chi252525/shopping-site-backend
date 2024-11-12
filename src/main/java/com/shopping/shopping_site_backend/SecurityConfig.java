package com.shopping.shopping_site_backend;

import com.shopping.shopping_site_backend.infra.sys.spring.filter.CustomAuthenticationFilter;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http.csrf(AbstractHttpConfigurer::disable) // 禁用 CSRF 保護 (請小心使用)
        .authorizeHttpRequests(authorize ->
            authorize
                .requestMatchers("/favicon.ico", "/api/oauth2/**", "/api/oauth2/callback","/swagger-ui/**",   "/swagger-ui/index.html", "/v3/api-docs/**", "/error")
                .permitAll() // 允許 login 頁面、OAuth2 路徑、Swagger UI 和 API 文件被無需驗證的方式訪問
                .anyRequest().authenticated() // 其他請求需要驗證
        )

        .oauth2Login(oauth2 ->
            oauth2
                .loginPage("/oauth2/authorization/google")
                .successHandler(customAuthenticationSuccessHandler()) // 自訂成功處理器
                .failureHandler(new SimpleUrlAuthenticationFailureHandler("/login?error=true"))
        );
//        .addFilterBefore(customAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class); // 加入自訂的驗證過濾器

    return http.build();
  }


  @Bean
  public CustomAuthenticationFilter customAuthenticationFilter() {
    return new CustomAuthenticationFilter();
  }

  @Bean
  public AuthenticationSuccessHandler customAuthenticationSuccessHandler() {
    return (request, response, authentication) -> {
      response.setStatus(HttpServletResponse.SC_OK);
      response.setContentType("application/json");
      response.setCharacterEncoding("UTF-8");
      response.getWriter().write("{\"status\":\"success\", \"message\":\"Login successful\"}");
      response.getWriter().flush();
    };
  }

  @Bean
  public AuthenticationManager authManager(HttpSecurity http) throws Exception {
    AuthenticationManagerBuilder authenticationManagerBuilder =
        http.getSharedObject(AuthenticationManagerBuilder.class);
    return authenticationManagerBuilder.build();
  }
}
