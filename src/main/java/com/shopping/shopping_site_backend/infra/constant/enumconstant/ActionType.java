package com.shopping.shopping_site_backend.infra.constant.enumconstant;

public enum ActionType {
  MODIIFY_QTY("異動數量"),
  MODIIFY_ITEM("異動商品"),
  MODIIFY_PAID("異動付款");
  private String description;

  ActionType(String description) {
    this.description = description;
  }
}
