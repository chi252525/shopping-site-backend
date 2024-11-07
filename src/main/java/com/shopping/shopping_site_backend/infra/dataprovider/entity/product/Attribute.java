package com.shopping.shopping_site_backend.infra.dataprovider.entity.product;

import javax.persistence.*;

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
@Table(name = "ec_attribute")
public class Attribute extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id; // Attribute ID, auto-increment primary key

    @Column(name = "sku", nullable = false)
    private String sku; // SKU

    @Column(name = "name", nullable = false)
    private String name; // Attribute name

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product; // Associated product

    @Column(name = "inventory", nullable = false)
    private double inventory; // Inventory

    @Column(name = "return_quantity_on_cancel", nullable = false)
    private boolean returnQuantityOnCancel; // Return quantity on cancel

    @Column(name = "alert_threshold", nullable = false)
    private int alertThreshold; // Alert threshold

    @Column(name = "price", nullable = false)
    private double price; // Price

    @Column(name = "discount_price")
    private double discountPrice; // Discount price

    @Column(name = "quantity", nullable = false)
    private int quantity; // Quantity
}