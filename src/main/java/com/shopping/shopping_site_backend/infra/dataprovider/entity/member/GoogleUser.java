package com.shopping.shopping_site_backend.infra.dataprovider.entity.member;

import com.shopping.shopping_site_backend.infra.sys.spring.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;


@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@DynamicUpdate
@Entity
public class GoogleUser extends BaseEntity {
    @Id
    @Column(columnDefinition = "VARCHAR(128)", nullable = false, updatable = false)
    private String id;
    @Column(columnDefinition = "VARCHAR(128)", nullable = false)
    private String name;
    @Column(columnDefinition = "VARCHAR(128)", nullable = false)
    private String email;

}