package com.shopping.shopping_site_backend.infra.constant.enumconstant;

public enum ActionType {
  MODIIFY_QTY("異動數量"),
  MODIIFY_ITEM("異動商品"),
  MODIIFY_PAID("異動付款金額"),
  MODIIFY_SHIPPING("異動運費"),
  MODIIFY_DISCOUNT("異動折扣"),
  MODIIFY_TOTAL("異動總金額"),
  MODIIFY_STATUS("異動狀態"),
  MODIIFY_SHIPPING_STATUS("異動出貨狀態"),
  MODIIFY_REFUND("異動退款"),
  MODIIFY_REFUND_STATUS("異動退款狀態"),
  MODIIFY_REFUND_RECORD("異動退款記錄"),
  MODIIFY_REFUND_RECORD_STATUS("異動退款記錄狀態"),
  MODIIFY_SHOP("異動出貨店家");
  private String description;

  ActionType(String description) {
    this.description = description;
  }
}
