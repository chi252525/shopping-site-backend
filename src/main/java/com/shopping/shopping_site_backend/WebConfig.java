package com.shopping.shopping_site_backend;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
  @Override
  public void addCorsMappings(CorsRegistry registry) {
    registry
        .addMapping("/**")
        .allowedOrigins(
            "https://shopping-site-front.vercel.app",
            "https://shopping-site-admin-front-lr33.vercel.app",
            "https://shopping-site-admin-front.vercel.app",
            "http://localhost:9000"
        )
        .allowedHeaders("Access-Control-Allow-Origin", "Cache-Control","Authorization", "Content-Type", "*")  // 允许的请求头
        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS");
  }

  @Bean
  public RestTemplate restTemplate() {
    return new RestTemplate();
  }
}
