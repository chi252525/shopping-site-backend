package com.shopping.shopping_site_backend.shoppingSite.front.api.intranet.order;

import com.shopping.shopping_site_backend.shoppingSite.front.api.intranet.order.v1_0.OrderTransactionFlowV1_0;
import com.shopping.shopping_site_backend.shoppingSite.front.controller.intranet.order.model.OrderTransactionResponseV1_0;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;



@Service
public class OrderTransactionPresentation {
    private final OrderTransactionFlowV1_0 orderTransactionFlowV1_0;

    public OrderTransactionPresentation(OrderTransactionFlowV1_0 orderTransactionFlowV10) {
        orderTransactionFlowV1_0 = orderTransactionFlowV10;
    }

    public OrderTransactionResponseV1_0 presentV1_0(@Valid OrderTransactionResponseV1_0 request)  {
        return this.orderTransactionFlowV1_0.execute(request);
    }
}
