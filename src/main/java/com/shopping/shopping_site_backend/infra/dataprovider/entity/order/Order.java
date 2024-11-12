package com.shopping.shopping_site_backend.infra.dataprovider.entity.order;

import com.shopping.shopping_site_backend.infra.dataprovider.entity.BaseEntity;
import com.shopping.shopping_site_backend.infra.dataprovider.entity.shopper.Shopper;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@DynamicUpdate
@Entity
@Table(name = "ec_order")
public class Order extends BaseEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id; // 自动递增的 ID

  @Column(name = "indicationOrderNumber", nullable = false)
  private String indicationOrderNumber; // 订单编号

  @Column(name = "orderDate", nullable = false)
  private LocalDateTime orderDate; // 订单日期

  @Column(name = "paymentDate")
  private LocalDateTime paymentDate; // 支付日期

  @Column(name = "paymentType")
  private String paymentType; // 支付类型

  @Column(name = "status", nullable = false)
  private String status = "Shipping Complete"; // 状态，默认值为 'Shipping Complete'

  @Column(name = "shopperType", nullable = false)
  private String shopperType;

  @Column(name = "shopperName", nullable = false)
  private String shopperName; // 购物者姓名

  @Column(name = "shopperPaidAmount", nullable = false)
  private Double shopperPaidAmount; // 购物者支付金额

  @Column(name = "discountTotal")
  private Double discountTotal = 0.0; // 折扣总额，默认值为 0.0

  @ManyToOne
  @JoinColumn(name = "shopper_id", nullable = false)
  private Shopper shopper; // Associated shopper
}
