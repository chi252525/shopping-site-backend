package com.shopping.shopping_site_backend.infra.sys.spring.repository;

import com.shopping.shopping_site_backend.infra.dataprovider.entity.product.Product;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository
    extends BaseRepository<Product, Long>, JpaSpecificationExecutor<Product> {}
