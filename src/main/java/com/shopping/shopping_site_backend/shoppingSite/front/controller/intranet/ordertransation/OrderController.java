package com.shopping.shopping_site_backend.shoppingSite.front.controller.intranet.ordertransation;

import com.shopping.shopping_site_backend.shoppingSite.front.api.intranet.order.OrderTransactionPresentation;
import com.shopping.shopping_site_backend.shoppingSite.front.controller.intranet.ordertransation.model.OrderTransactionRequestV1_0;
import com.shopping.shopping_site_backend.shoppingSite.front.controller.intranet.ordertransation.model.OrderTransactionResponseV1_0;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "訂單交易")
@RequiredArgsConstructor
@RestController
@RequestMapping("${intranet}/order")
public class OrderController {
  private final OrderTransactionPresentation orderTransactionPresentation;

  @Operation(
      tags = {"訂單交易"},
      summary = "訂單交易",
      description = "下單")
  @PostMapping("/orderTransaction")
  public OrderTransactionResponseV1_0 orderTransaction(
      @Parameter(required = true) @Valid @RequestBody OrderTransactionRequestV1_0 request) {
    return this.orderTransactionPresentation.presentV1_0(request);
  }

  @Operation(
      tags = {"訂單交易"},
      summary = "取消訂單",
      description = "取消訂單")
  @PostMapping("/cancelOrder")
  public OrderTransactionResponseV1_0 cancelOrder(
      @Parameter(required = true) @Valid @RequestBody OrderTransactionRequestV1_0 request) {
    return this.orderTransactionPresentation.cancelOrder(request);
  }

  @Operation(
      tags = {"訂單交易"},
      summary = "退款訂單",
      description = "退款訂單")
  @PostMapping("/refundOrder")
  public OrderTransactionResponseV1_0 refundOrder(
      @Parameter(required = true) @Valid @RequestBody OrderTransactionRequestV1_0 request) {
    return this.orderTransactionPresentation.refundOrder(request);
  }
}
