package com.shopping.shopping_site_backend.infra.dataprovider.entity.shopper;

import com.shopping.shopping_site_backend.infra.sys.spring.entity.BaseEntity;
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
@Table(name = "google_user")
public class GoogleUser extends BaseEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(columnDefinition = "VARCHAR(128)", nullable = false)
  private String name;

  @Column(columnDefinition = "VARCHAR(128)", nullable = false)
  private String email;
}
