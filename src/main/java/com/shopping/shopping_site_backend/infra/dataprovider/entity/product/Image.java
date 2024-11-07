package com.shopping.shopping_site_backend.infra.dataprovider.entity.product;

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
@Table(name = "ec_image")
public class Image extends BaseEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id; // 图片 ID

  @Column(name = "product_id", nullable = false)
  private Integer productId; // 关联的产品 ID

  @Column(name = "image_url", nullable = false)
  private String imageUrl; // 图片 URL

  @Column(name = "img_alt")
  private String imgAlt; // 图片替代文本

  @ManyToOne
  @JoinColumn(name = "product_id", nullable = false)
  private Product product; // 关联的产品


}
