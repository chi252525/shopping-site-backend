package com.shopping.shopping_site_backend.shoppingSite.front.controller.intranet.myorder;

import com.shopping.shopping_site_backend.infra.dataprovider.entity.order.Order;
import com.shopping.shopping_site_backend.shoppingSite.front.api.intranet.myorder.MyOrderPresentation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "我的訂單")
@RequiredArgsConstructor
@RestController
@RequestMapping("${intranet}/my-order")
public class MyOrderController {

    private final MyOrderPresentation myOrderPresentation;

    @GetMapping("/{id}")
    public ResponseEntity<Order> getMyOrderById(@PathVariable("id") Long id) {
      Order order = myOrderPresentation.getMyOrderById(id);
      return order != null ? ResponseEntity.ok(order) : ResponseEntity.notFound().build();
    }

  @GetMapping("/shopper/{shopperId}")
  public ResponseEntity<List<Order>> getMyOrderByShopperId(@PathVariable("shopperId") Long shopperId) {
    List<Order> orders = myOrderPresentation.getMyOrderByShopperId(shopperId);
    return orders != null && !orders.isEmpty() ? ResponseEntity.ok(orders) : ResponseEntity.notFound().build();
  }
}
