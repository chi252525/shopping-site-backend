
package com.shopping.shopping_site_backend.infra.dataprovider.entity.refund;

import javax.persistence.*;
import java.time.LocalDateTime;

import com.shopping.shopping_site_backend.infra.constant.enumconstant.PaymentType;
import com.shopping.shopping_site_backend.infra.dataprovider.entity.order.Order;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "ec_refund_record")
public class RefundRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id; // RefundRecord ID, auto-increment primary key

    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    private Order order; // Associated order

    @Column(name = "status", nullable = false)
    private String status; // Status

    @Column(name = "complete_time")
    private LocalDateTime completeTime; // Completion time

    @Enumerated(EnumType.STRING)
    @Column(name = "payment_type", nullable = false)
    private PaymentType paymentType; // Payment type
}