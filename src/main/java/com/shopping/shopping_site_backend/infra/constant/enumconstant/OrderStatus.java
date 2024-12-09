package com.shopping.shopping_site_backend.infra.constant.enumconstant;

public enum OrderStatus {
  PENDING("Pending"),
  CONFIRMED("Confirmed"), // 已确认
  SHIPPED("Shipped"),
  DELIVERED("Delivered"),
  CANCELED("Canceled"),
  REFUNDED("Refunded"); // 已退款;
  private String description;

  OrderStatus(String description) {
    this.description = description;
  }
}
