package com.shopping.shopping_site_backend.infra.constant.enumconstant;

public enum PaymentType {

    CREDIT_CARD("Credit Card"),  // 信用卡
    BANK_TRANSFER("Bank Transfer"),  // 银行转账
    CASH_ON_DELIVERY("Cash on Delivery") // 货到付款
    ;
    private String description;
    PaymentType(String description) {
        this.description = description;
    }
}
