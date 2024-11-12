package com.shopping.shopping_site_backend.infra.constant.enumconstant;

public enum PaymentStatus {
  PENDING("Pending"), // 待支付
  COMPLETED("Completed"), // 已完成
  FAILED("Failed"), // 失败
  REFUNDED("Refunded");
  private String description;

  PaymentStatus(String description) {
    this.description = description;
  }
}
