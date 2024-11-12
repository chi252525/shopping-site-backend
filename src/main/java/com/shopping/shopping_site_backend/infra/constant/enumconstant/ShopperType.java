package com.shopping.shopping_site_backend.infra.constant.enumconstant;

public enum ShopperType {
  NORMAL("Normal"), // 普通
  VIP("VIP"); // VIP
  private String description;

  ShopperType(String description) {
    this.description = description;
  }
}
