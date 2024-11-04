package com.shopping.shopping_site_backend.infra.dataprovider.entity.product;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import org.springframework.data.annotation.Id;

@Entity
public class Image {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id; // 图片 ID

  @Column(name = "product_id", nullable = false)
  private Integer productId; // 关联的产品 ID

  @Column(name = "image_url", nullable = false)
  private String imageUrl; // 图片 URL

  @Column(name = "img_alt")
  private String imgAlt; // 图片替代文本
}
