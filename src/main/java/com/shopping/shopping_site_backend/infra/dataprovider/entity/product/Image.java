package com.shopping.shopping_site_backend.infra.dataprovider.entity.product;

import com.shopping.shopping_site_backend.infra.dataprovider.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

import java.util.ArrayList;
import java.util.List;

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

  @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
  @ToString.Exclude // 防止循环引用
  private List<Image> images = new ArrayList<>(); // 关联的图片列表

}
