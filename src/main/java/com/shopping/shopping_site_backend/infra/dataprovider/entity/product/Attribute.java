package com.shopping.shopping_site_backend.infra.dataprovider.entity.product;

import com.shopping.shopping_site_backend.infra.dataprovider.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
@Table(name = "ec_attribute")
public class Attribute extends BaseEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id; // Attribute ID, auto-increment primary key

  @Column(name = "sku", nullable = false)
  private String sku; // SKU

  @Column(name = "name", nullable = false)
  private String name; // Attribute name

  @ManyToOne
  @JoinColumn(name = "product_id", nullable = false)
  private Product product; // Associated product

  @Column(name = "inventory", nullable = false)
  private double inventory; // Inventory



}
