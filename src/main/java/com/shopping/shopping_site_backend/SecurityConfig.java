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
    http.csrf(csrf -> csrf.disable()) // Disable CSRF protection (use carefully)
        .authorizeHttpRequests(authorize ->
            authorize
                .requestMatchers("/login", "/api/oauth2/**", "/swagger-ui/**", "/v3/api-docs/**")
                .permitAll() // Allow login page, OAuth2 paths, Swagger UI, and API docs to be accessed without authentication
                .anyRequest().authenticated() // Other requests require authentication
        )
        .oauth2Login(oauth2 ->
            oauth2
                .loginPage("/oauth2/authorization/google")
                .successHandler(customAuthenticationSuccessHandler()) // Custom success handler
                .failureHandler(new SimpleUrlAuthenticationFailureHandler("/login?error=true"))
        )
        .addFilterBefore(customAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class); // Add custom filter

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
