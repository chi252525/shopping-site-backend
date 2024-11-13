package com.shopping.shopping_site_backend.infra.dataprovider.entity.order;

import com.shopping.shopping_site_backend.infra.constant.enumconstant.PaymentStatus;
import com.shopping.shopping_site_backend.infra.constant.enumconstant.PaymentType;
import com.shopping.shopping_site_backend.infra.constant.enumconstant.ShippingStatus;
import com.shopping.shopping_site_backend.infra.constant.enumconstant.ShippingType;
import com.shopping.shopping_site_backend.infra.dataprovider.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
@Table(name = "ec_order_item")
public class OrderItem extends BaseEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "order_id", nullable = false)
  private Order order;

  @Column(name = "sku", nullable = false)
  private String sku;

  @Column(name = "discount_price", nullable = false)
  private double discountPrice;

  @Column(name = "quantity", nullable = false)
  private int quantity;
}
