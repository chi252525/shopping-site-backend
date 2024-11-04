package com.shopping.shopping_site_backend.infra.dataprovider.entity.shopper;

import com.shopping.shopping_site_backend.infra.sys.spring.entity.BaseEntity;
import javax.persistence.Entity;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;

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
