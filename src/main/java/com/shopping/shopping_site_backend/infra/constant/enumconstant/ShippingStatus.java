package com.shopping.shopping_site_backend.infra.constant.enumconstant;

public enum ShippingStatus {
  PENDING("Pending"),
  SHIPPED("Shipped"),
  DELIVERED("Delivered"),
  CANCELED("Canceled"),
  RETURNED("Returned");
  private String description;

  ShippingStatus(String description) {
    this.description = description;
  }
}
