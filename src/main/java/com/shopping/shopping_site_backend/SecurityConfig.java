package com.shopping.shopping_site_backend;

import com.shopping.shopping_site_backend.infra.sys.spring.filter.CustomAuthenticationFilter;
import jakarta.servlet.http.HttpServletRequest;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.web.DefaultOAuth2AuthorizationRequestResolver;
import org.springframework.security.oauth2.client.web.OAuth2AuthorizationRequestResolver;
import org.springframework.security.oauth2.core.endpoint.OAuth2AuthorizationRequest;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
  @Autowired private ClientRegistrationRepository clientRegistrationRepository;

//  @Bean
//  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//    DefaultOAuth2AuthorizationRequestResolver defaultAuthorizationRequestResolver =
//        new DefaultOAuth2AuthorizationRequestResolver(
//            clientRegistrationRepository, "/oauth2/authorization");
//
//    // 定義自定義的範圍
//    OAuth2AuthorizationRequestResolver customAuthorizationRequestResolver =
//        new OAuth2AuthorizationRequestResolver() {
//          @Override
//          public OAuth2AuthorizationRequest resolve(HttpServletRequest request) {
//            OAuth2AuthorizationRequest authorizationRequest =
//                defaultAuthorizationRequestResolver.resolve(request);
//            return customizeAuthorizationRequest(authorizationRequest);
//          }
//
//          @Override
//          public OAuth2AuthorizationRequest resolve(
//              HttpServletRequest request, String clientRegistrationId) {
//            OAuth2AuthorizationRequest authorizationRequest =
//                defaultAuthorizationRequestResolver.resolve(request, clientRegistrationId);
//            return customizeAuthorizationRequest(authorizationRequest);
//          }
//        };
//    // 啟用 CORS 配置
//
//    http.cors(cors -> cors.configurationSource(corsConfigurationSource()))
//        .csrf(AbstractHttpConfigurer::disable)
//            .authorizeHttpRequests(
//            authorize ->
//                authorize// 允許 login 頁面、OAuth2 路徑、Swagger UI 和 API 文件被無需驗證的方式訪問
//                    .anyRequest()
//                    .permitAll() // 其他請求需要驗證
//            )
//        .oauth2Login(
//            oauth2 ->
//                oauth2
//                    .loginPage("/oauth2/authorization/google")
//                    .authorizationEndpoint(
//                        authorizationEndpoint ->
//                            authorizationEndpoint.authorizationRequestResolver(
//                                customAuthorizationRequestResolver))
//                    //                .successHandler(customAuthenticationSuccessHandler()) //
//                    // 自訂成功處理器
//                    .failureHandler(
//                        new SimpleUrlAuthenticationFailureHandler("/login?error=true")));
//    //        .addFilterBefore(customAuthenticationFilter(),
//    // UsernamePasswordAuthenticationFilter.class); // 加入自訂的驗證過濾器
//
//    return http.build();
//  }

  private OAuth2AuthorizationRequest customizeAuthorizationRequest(
      OAuth2AuthorizationRequest authorizationRequest) {
    if (authorizationRequest != null) {
      // 設置所需的範圍
      Set<String> scopes =
          Set.of(
              "https://www.googleapis.com/auth/userinfo.profile",
              "https://www.googleapis.com/auth/userinfo.email");
      return OAuth2AuthorizationRequest.from(authorizationRequest).scopes(scopes).build();
    }
    return authorizationRequest;
  }

  @Bean
  public CustomAuthenticationFilter customAuthenticationFilter() {
    return new CustomAuthenticationFilter();
  }
  // Define CORS configuration
  @Bean
  public UrlBasedCorsConfigurationSource corsConfigurationSource() {
    CorsConfiguration configuration = new CorsConfiguration();
    configuration.setAllowedOrigins(List.of(
        "http://localhost:*", "https://localhost:*", "https://*.vercel.app"
    ));
    configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
    configuration.setAllowedHeaders(List.of("*"));
    configuration.setAllowCredentials(true);
    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    source.registerCorsConfiguration("/**", configuration);
    return source;
  }

  // Security configuration using SecurityFilterChain
  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http
        .cors(cors -> cors.configurationSource(corsConfigurationSource())) // Set CORS configuration
        .csrf(AbstractHttpConfigurer::disable) // Disable CSRF protection
        .authorizeHttpRequests(authorize ->
            authorize
                .requestMatchers("/login", "/oauth2/authorization/google", "/swagger-ui/**", "/v3/api-docs/**") // Allow public access to login, OAuth2, Swagger UI, and API docs
                .permitAll() // No authentication required for these paths
                .anyRequest() // All other requests require authentication
                .authenticated()
        );
    return http.build();
  }
  //  @Bean
  //  public AuthenticationSuccessHandler customAuthenticationSuccessHandler() {
  //    return (request, response, authentication) -> {
  //      response.setStatus(HttpServletResponse.SC_OK);
  //      response.setContentType("application/json");
  //      response.setCharacterEncoding("UTF-8");
  //      response.getWriter().write("{\"status\":\"success\", \"message\":\"Login successful\"}");
  //      response.getWriter().flush();
  //    };
  //  }

  @Bean
  public AuthenticationManager authManager(HttpSecurity http) throws Exception {
    AuthenticationManagerBuilder authenticationManagerBuilder =
        http.getSharedObject(AuthenticationManagerBuilder.class);
    return authenticationManagerBuilder.build();
  }
}
