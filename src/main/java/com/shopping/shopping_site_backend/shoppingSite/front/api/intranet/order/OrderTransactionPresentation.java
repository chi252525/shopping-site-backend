package com.shopping.shopping_site_backend.shoppingSite.front.api.intranet.order;

import com.shopping.shopping_site_backend.shoppingSite.front.api.intranet.order.v1_0.OrderTransactionFlowV1_0;
import com.shopping.shopping_site_backend.shoppingSite.front.controller.intranet.ordertransation.model.OrderTransactionRequestV1_0;
import com.shopping.shopping_site_backend.shoppingSite.front.controller.intranet.ordertransation.model.OrderTransactionResponseV1_0;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

@Service
public class OrderTransactionPresentation {
  private final OrderTransactionFlowV1_0 orderTransactionFlowV1_0;

  public OrderTransactionPresentation(OrderTransactionFlowV1_0 orderTransactionFlowV1) {
    orderTransactionFlowV1_0 = orderTransactionFlowV1;
  }

  public OrderTransactionResponseV1_0 presentV1_0(@Valid OrderTransactionRequestV1_0 request) {
    return this.orderTransactionFlowV1_0.execute(request);
  }

  public OrderTransactionResponseV1_0 cancelOrder(@Valid OrderTransactionRequestV1_0 request) {
    return null;
  }

  public OrderTransactionResponseV1_0 refundOrder(@Valid OrderTransactionRequestV1_0 request) {
    return null;
  }

  public OrderTransactionResponseV1_0 updateOrderStatus(Long id, String status) {
    return null;
  }
}
