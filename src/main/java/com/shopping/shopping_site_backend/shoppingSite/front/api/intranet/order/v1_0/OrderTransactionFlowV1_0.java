package com.shopping.shopping_site_backend.shoppingSite.front.api.intranet.order.v1_0;

import com.shopping.shopping_site_backend.shoppingSite.front.controller.intranet.ordertransation.model.OrderTransactionRequestV1_0;
import com.shopping.shopping_site_backend.shoppingSite.front.controller.intranet.ordertransation.model.OrderTransactionResponseV1_0;
import com.shopping.shopping_site_backend.shoppingSite.front.infrastructure.gateway.PaymentGateway;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class OrderTransactionFlowV1_0 {
  private final PaymentGateway paymentGateway;

  public OrderTransactionResponseV1_0 execute(@Valid OrderTransactionRequestV1_0 request) {

    // gateWay串交易

    // UseCase 回給member

    return new OrderTransactionResponseV1_0();
  }
}
