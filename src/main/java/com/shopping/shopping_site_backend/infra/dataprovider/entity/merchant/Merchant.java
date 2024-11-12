package com.shopping.shopping_site_backend.infra.dataprovider.entity.merchant;

import com.shopping.shopping_site_backend.infra.dataprovider.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
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
