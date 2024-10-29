package com.shopping.shopping_site_backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("com.shopping.shopping_site_backend.infra.sys.spring.repository")
@EntityScan("com.shopping.shopping_site_backend.infra.dataprovider.entity") // Adjust the package to where your entity is defined
public class ShoppingSiteBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShoppingSiteBackendApplication.class, args);
	}

}
