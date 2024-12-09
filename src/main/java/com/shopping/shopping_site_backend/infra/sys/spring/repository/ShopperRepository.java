package com.shopping.shopping_site_backend.infra.sys.spring.repository;

import com.shopping.shopping_site_backend.infra.dataprovider.entity.shopper.Shopper;
import org.springframework.stereotype.Repository;

@Repository
public interface ShopperRepository extends BaseRepository<Shopper, Long> {
  Shopper findByEmail(String email);
}
