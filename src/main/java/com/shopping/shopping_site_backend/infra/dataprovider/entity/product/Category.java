package com.shopping.shopping_site_backend.infra.dataprovider.entity.product;

import com.shopping.shopping_site_backend.infra.dataprovider.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
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
@Table(name = "ec_category")
public class Category extends BaseEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id; // 分类 ID

  @Column(name = "enabled", nullable = false)
  private Boolean enabled; // 是否启用

  @Column(name = "name", nullable = false)
  private String name; // 分类名称

  @ManyToOne
  @JoinColumn(name = "product_id", nullable = false)
  private Product product; // 关联的产品
  // 关联的产品 ID

  @ManyToOne
  @MapsId
  @JoinColumn(name = "id")
  private Category parentCategory; // 关联的父级分类
}
