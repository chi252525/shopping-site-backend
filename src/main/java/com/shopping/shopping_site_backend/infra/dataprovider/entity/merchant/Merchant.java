package com.shopping.shopping_site_backend.infra.dataprovider.entity.merchant;

import javax.persistence.*;

import com.shopping.shopping_site_backend.infra.dataprovider.entity.BaseEntity;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.annotation.Id;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@DynamicUpdate
@Entity
@Table(name = "ec_merchant")
public class Merchant extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Merchant ID, auto-increment primary key

    @Column(name = "name", nullable = false)
    private String name; // Merchant name

    @Column(name = "enabled", nullable = false)
    private Boolean enabled; // Whether the merchant is enabled
}