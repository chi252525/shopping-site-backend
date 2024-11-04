package com.shopping.shopping_site_backend.infra.dataprovider.entity.product;

import jakarta.persistence.Entity;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
public class Category {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id; // 分类 ID

  @Column(name = "enabled", nullable = false)
  private Boolean enabled; // 是否启用

  @Column(name = "name", nullable = false)
  private String name; // 分类名称

  @Column(name = "parent_id")
  private Integer parentId; // 父级分类 ID

  @Column(name = "product_id")
  private Integer productId; // 关联的产品 ID
}
