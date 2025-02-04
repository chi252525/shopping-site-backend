package com.shopping.shopping_site_backend.infra.sys.spring.repository;

import com.shopping.shopping_site_backend.infra.dataprovider.entity.product.Category;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

  List<Category> findByEnabledTrue();
}