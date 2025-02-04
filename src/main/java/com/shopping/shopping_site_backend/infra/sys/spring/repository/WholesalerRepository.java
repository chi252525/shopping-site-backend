package com.shopping.shopping_site_backend.infra.sys.spring.repository;

import com.shopping.shopping_site_backend.infra.dataprovider.entity.wholesaler.Wholesaler;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WholesalerRepository extends JpaRepository<Wholesaler, Long> {}
