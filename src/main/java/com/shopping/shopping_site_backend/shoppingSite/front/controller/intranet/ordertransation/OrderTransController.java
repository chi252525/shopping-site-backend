package com.shopping.shopping_site_backend.shoppingSite.front.controller.intranet.ordertransation;

import com.shopping.shopping_site_backend.shoppingSite.front.api.intranet.order.OrderTransactionPresentation;
import com.shopping.shopping_site_backend.shoppingSite.front.controller.intranet.ordertransation.model.OrderTransactionRequestV1_0;
import com.shopping.shopping_site_backend.shoppingSite.front.controller.intranet.ordertransation.model.OrderTransactionResponseV1_0;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "訂單交易")
@RequiredArgsConstructor
@RestController
@RequestMapping("${intranet}/order")
public class OrderTransController {
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

  //更新訂單狀態（Update Order Status）
//  方法：PUT
////  路徑：/orders/{id}/status
////  描述：更新訂單的狀態，常見的狀態有「待處理」、「已發貨」、「已取消」等。
////  參數：id（訂單ID）、status（訂單狀態）
////  返回：更新後的訂單
  @Operation(
      tags = {"訂單交易"},
      summary = "更新訂單狀態",
      description = "更新訂單狀態")
  @PutMapping("/{id}/status")
  public OrderTransactionResponseV1_0 updateOrderStatus(
      @Parameter(description = "訂單ID", required = true) @PathVariable("id") Long id,
      @Parameter(description = "訂單狀態", required = true) @RequestParam("status") String status) {
    return this.orderTransactionPresentation.updateOrderStatus(id, status);
  }






}
