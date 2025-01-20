package com.shopping.shopping_site_backend.infra.dataprovider.entity.order;

import com.shopping.shopping_site_backend.infra.constant.enumconstant.PaymentStatus;
import com.shopping.shopping_site_backend.infra.constant.enumconstant.PaymentType;
import com.shopping.shopping_site_backend.infra.constant.enumconstant.ShippingStatus;
import com.shopping.shopping_site_backend.infra.constant.enumconstant.ShippingType;
import com.shopping.shopping_site_backend.infra.dataprovider.entity.BaseEntity;
import com.shopping.shopping_site_backend.infra.dataprovider.entity.shopper.Shopper;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.util.List;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;
import reactor.core.publisher.Sinks.Many;

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
  private String indicationOrderNumber;

  @Column(name = "orderDate", nullable = false)
  private LocalDateTime orderDate;

  @Column(name = "paymentDate")
  private LocalDateTime paymentDate;

  @Enumerated(EnumType.STRING)
  @Column(name = "shipping_type", nullable = false)
  private ShippingType shippingType;

  @Enumerated(EnumType.STRING)
  @Column(name = "shipping_status", nullable = false)
  private ShippingStatus shippingStatus;

  @Enumerated(EnumType.STRING)
  @Column(name = "payment_type", nullable = false)
  private PaymentType paymentType;

  @Enumerated(EnumType.STRING)
  @Column(name = "payment_status", nullable = false)
  private PaymentStatus paymentStatus;

  @Column(name = "shopperType", nullable = false)
  private String shopperType;

  @Column(name = "shopperName", nullable = false)
  private String shopperName;

  @Column(name = "shopperPaidAmount", nullable = false)
  private Double shopperPaidAmount;

  @Column(name = "discountTotal")
  private Double discountTotal = 0.0;

  @Column(name = "shipping_fee", nullable = false)
  private Double shippingFee;

  @Column(name = "shopper_phone", nullable = false)
  private String shopperPhone;

  @Column(name = "shopperEmail", nullable = false)
  private String shopperEmail;

  @Column(name = "receiver_address")
  private String receiverAddress;

  @Column(name = "receiver_name", nullable = false)
  private String receiverName;

  @Column(name = "receiver_phone", nullable = false)
  private String receiverPhone;

  @Column(name = "receiver_shop_id")
  private String receiverShopId;

  @Column(name = "receiver_shop_name")
  private String receiverShopName;

  @Column(name = "receiver_shop_address")
  private String receiverShopAddress;

  @ManyToOne
  @JoinColumn(name = "shopper_id", nullable = false)
  private Shopper shopper;

  @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<OrderItem> orderItems;


}
