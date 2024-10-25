package com.shopping.shopping_site_backend;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.jdbc.core.JdbcTemplate;

import static org.assertj.core.api.Assertions.assertThat;

//@SpringBootTest
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE) // 使用实际数据库
//public class DatabaseConnectionTest {
//
//    @Autowired
//    private JdbcTemplate jdbcTemplate;
//
//    @Test
//    public void testDatabaseConnection() {
//        Integer result = jdbcTemplate.queryForObject("SELECT 1", Integer.class);
//        assertThat(result).isEqualTo(1); // 验证查询结果
//    }
//}
