package com.shopping.shopping_site_backend.shoppingSite.front.api.intranet.myorder;

import com.shopping.shopping_site_backend.infra.dataprovider.entity.order.Order;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class MyOrderPresentation {

  public Order getMyOrderById(Long id) {
    return null;
  }

  public List<Order> getMyOrderByShopperId(Long shopperId) {
    return List.of();
  }
}
