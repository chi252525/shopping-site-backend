package com.shopping.shopping_site_backend;

import com.shopping.shopping_site_backend.infra.sys.spring.filter.CustomAuthenticationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;

import java.util.List;
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // 禁用 CSRF 保護（視情況而定）
                .authorizeHttpRequests(authorize ->
                        authorize
                                .requestMatchers("/login", "/oauth2/**","/swagger-ui/**", "/v3/api-docs/**").permitAll() // 允許訪問登錄頁面和 OAuth2 路徑
                                .anyRequest().authenticated() // 其他請求需身份驗證
                )
                .oauth2Login(oauth2 ->
                        oauth2
//                        前端會處理用戶界 面，後端只負責身份驗證
                                .defaultSuccessUrl("/home", true) // 登錄成功後的重定向頁面
                                .failureUrl("/login?error=true")
                )
                .addFilterBefore(customAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class); // 添加自定義過濾器

        return http.build();
    }
    @Bean
    public CustomAuthenticationFilter customAuthenticationFilter() {
        return new CustomAuthenticationFilter();
    }
    @Bean
    public AuthenticationManager authManager(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder =
                http.getSharedObject(AuthenticationManagerBuilder.class);
        return authenticationManagerBuilder.build();
    }
}
