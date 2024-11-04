package com.shopping.shopping_site_backend;

import com.shopping.shopping_site_backend.infra.sys.spring.filter.CustomAuthenticationFilter;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // 禁用 CSRF 保護（視情況而定）
                .authorizeHttpRequests(authorize ->
                        authorize
                                .requestMatchers("/login", "/api/oauth2/**","/swagger-ui/**", "/v3/api-docs/**").permitAll() // 允許訪問登錄頁面和 OAuth2 路徑
                                .anyRequest().authenticated() // 其他請求需身份驗證
                )
                .oauth2Login(oauth2 ->
                        oauth2
                                .loginPage("/oauth2/authorization/google")
                                .successHandler(customAuthenticationSuccessHandler()) // 使用自定義的成功處理程序
                                .failureHandler(new SimpleUrlAuthenticationFailureHandler("/login?error=true"))
                )
                .addFilterBefore(customAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class); // 添加自定義過濾器

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
