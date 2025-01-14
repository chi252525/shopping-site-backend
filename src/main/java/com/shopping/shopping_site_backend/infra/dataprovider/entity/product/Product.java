package com.shopping.shopping_site_backend.infra.dataprovider.entity.product;

import com.shopping.shopping_site_backend.infra.dataprovider.entity.BaseEntity;
import com.shopping.shopping_site_backend.infra.dataprovider.entity.merchant.Merchant;
import com.shopping.shopping_site_backend.infra.dataprovider.entity.merchant.Wholesaler;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

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
  private Long id;

  @Column(name = "base_sku", nullable = false)
  private String baseSku;

  @Column(name = "name", nullable = false)
  private String name;

  @Column(name = "unit_price", nullable = false)
  private Double unitPrice;

  @Column(name = "sale_price")
  private Double salePrice;

  @Column(name = "discount_price")
  private Double discountPrice;

  @Column(name = "version_id", nullable = false)
  private String versionId;

  @Column(name = "unit", nullable = false)
  private String unit;

  @Column(name = "in_stock", nullable = false)
  private Boolean inStock;

  @Column(name = "is_show", nullable = false)
  private Boolean isShow;

  @Column(name = "available_start_time")
  private LocalDateTime availableStartTime;

  @Column(name = "available_end_time")
  private LocalDateTime availableEndTime;

  @Column(name = "description")
  private String description;

  @Column(name = "is_settled")
  private Boolean isSettled;

  @Column(name = "is_old")
  private Boolean isOld;

  @ManyToOne
  @JoinColumn(name = "merchant_id", nullable = false)
  private Merchant merchant;

  @ManyToOne
  @JoinColumn(name = "first_category_id", nullable = false)
  private Category firstCategory;

  @ManyToOne
  @JoinColumn(name = "second_category_id", nullable = false)
  private Category secondCategory;

  @ManyToOne
  @JoinColumn(name = "third_category_id", nullable = false)
  private Category thirdCategory;

  @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
  @ToString.Exclude // 防止循环引用
  private List<Image> images = new ArrayList<>(); // 关联的图片列表

  @Column(name = "estimated_total_profit")
  private Double estimatedTotalProfit;

  @ManyToOne
  @JoinColumn(name = "wholesaler_id")
  private Wholesaler wholesaler;


}
