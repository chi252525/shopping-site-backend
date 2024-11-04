package com.shopping.shopping_site_backend;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition
@Configuration
public class SwaggerConfig {

  @Bean
  public OpenAPI baseOpenAPI() {
    return new OpenAPI()
        .info(
            new Info()
                .title("Ironman202 API Documentation ")
                .description("SpringBoot 3.x application")
                .version("v0.0.1")
                .license(new License().name("Your License").url("http://springdoc.org"))
                .contact(
                    new Contact()
                        .name("Ian Liu")
                        .email("ianliu@example.com")
                        .url("Your Website URl")));
  }
}
