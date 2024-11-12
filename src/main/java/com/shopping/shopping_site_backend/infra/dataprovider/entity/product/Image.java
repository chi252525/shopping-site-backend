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
@Table(name = "ec_image")
public class Image extends BaseEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id; // 图片 ID

  @Column(name = "image_url", nullable = false)
  private String imageUrl; // 图片 URL

  @Column(name = "img_alt")
  private String imgAlt; // 图片替代文本

  @ManyToOne
  @JoinColumn(name = "product_id", nullable = false)
  private Product product; // 关联的产品
}
