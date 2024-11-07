package com.shopping.shopping_site_backend.infra.dataprovider.entity.product;

import javax.persistence.*;
import java.time.LocalDateTime;

import com.shopping.shopping_site_backend.infra.dataprovider.entity.BaseEntity;
import com.shopping.shopping_site_backend.infra.dataprovider.entity.merchant.Merchant;
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
@Table(name = "ec_product")
public class Product extends BaseEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id; // 产品 ID，自增主键

  @Column(name = "base_sku", nullable = false)
  private String baseSku; // 基本 SKU

  @Column(name = "name", nullable = false)
  private String name; // 产品名称

  @Column(name = "unit_price", nullable = false)
  private Double unitPrice; // 单位价格

  @Column(name = "sale_price")
  private Double salePrice; // 销售价格

  @Column(name = "discount_price")
  private Double discountPrice; // 折扣价格

  @Column(name = "version_id", nullable = false)
  private Integer versionId; // 版本 ID

  @Column(name = "unit", nullable = false)
  private String unit; // 单位

  @Column(name = "in_stock", nullable = false)
  private Boolean inStock; // 是否有库存

  @Column(name = "available_start_time")
  private LocalDateTime availableStartTime; // 可用开始时间

  @Column(name = "available_end_time")
  private LocalDateTime availableEndTime; // 可用结束时间

  @ManyToOne
  @JoinColumn(name = "merchant_id", nullable = false)
  private Merchant merchant; // 关联的商家


}
