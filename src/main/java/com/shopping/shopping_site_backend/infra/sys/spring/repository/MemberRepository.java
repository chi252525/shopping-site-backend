package com.shopping.shopping_site_backend.infra.sys.spring.repository;


import com.shopping.shopping_site_backend.infra.dataprovider.entity.member.Shopper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Shopper, Long> {
    Shopper findByEmail(String email);
}
