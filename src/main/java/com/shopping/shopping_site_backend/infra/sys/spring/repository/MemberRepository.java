package com.shopping.shopping_site_backend.infra.sys.spring.repository;

import com.shopping.shopping_site_backend.infra.dataprovider.entity.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
    Member findByEmail(String email);
}
