package com.shopping.shopping_site_backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan(basePackages = "com.shopping.shopping_site_backend.infra.dataprovider.entity")
@EnableJpaRepositories(
    basePackages = "com.shopping.shopping_site_backend.infra.sys.spring.repository")
public class ShoppingSiteBackendApplication {
  public static void main(String[] args) {
    SpringApplication.run(ShoppingSiteBackendApplication.class, args);
  }
}
