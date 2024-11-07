package com.shopping.shopping_site_backend.infra.dataprovider.entity.order;

import javax.persistence.*;

import com.shopping.shopping_site_backend.infra.constant.enumconstant.PaymentStatus;
import com.shopping.shopping_site_backend.infra.constant.enumconstant.PaymentType;
import com.shopping.shopping_site_backend.infra.constant.enumconstant.ShippingStatus;
import com.shopping.shopping_site_backend.infra.constant.enumconstant.ShippingType;
import com.shopping.shopping_site_backend.infra.dataprovider.entity.BaseEntity;
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
    private Long id; // OrderItem ID, auto-increment primary key

    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    private Order order; // Associated order

    @Enumerated(EnumType.STRING)
    @Column(name = "shipping_type", nullable = false)
    private ShippingType shippingType; // Shipping type

    @Enumerated(EnumType.STRING)
    @Column(name = "shipping_status", nullable = false)
    private ShippingStatus shippingStatus; // Shipping status

    @Enumerated(EnumType.STRING)
    @Column(name = "payment_type", nullable = false)
    private PaymentType paymentType; // Payment type

    @Enumerated(EnumType.STRING)
    @Column(name = "payment_status", nullable = false)
    private PaymentStatus paymentStatus; // Payment status

    @Column(name = "sku", nullable = false)
    private String sku; // SKU

    @Column(name = "discount_price", nullable = false)
    private double discountPrice; // Discount price

    @Column(name = "quantity", nullable = false)
    private int quantity; // Quantity
}