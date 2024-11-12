// package com.shopping.shopping_site_backend;
//
// import jakarta.persistence.EntityManagerFactory;
// import javax.sql.DataSource;
// import org.springframework.boot.autoconfigure.domain.EntityScan;
// import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
// import org.springframework.orm.jpa.JpaTransactionManager;
// import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
// import org.springframework.transaction.PlatformTransactionManager;
// import org.springframework.transaction.annotation.EnableTransactionManagement;
//
// @Configuration
// @EnableTransactionManagement
// @EnableJpaRepositories(
//    basePackages = "com.shopping.shopping_site_backend.infra.sys.spring.repository")
// @EntityScan(basePackages = "com.shopping.shopping_site_backend.infra.dataprovider.entity")
// public class JpaConfig {
//
//  @Bean
//  public LocalContainerEntityManagerFactoryBean entityManagerFactory(
//      EntityManagerFactoryBuilder builder, DataSource dataSource) {
//    return builder
//        .dataSource(dataSource)
//        .packages("com.shopping.shopping_site_backend.infra.dataprovider.entity")
//        .persistenceUnit("shoppingSitePU")
//        .build();
//  }
//
//  @Bean
//  public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory)
// {
//    return new JpaTransactionManager(entityManagerFactory);
//  }
// }
