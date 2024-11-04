package com.shopping.shopping_site_backend.infra.constant.enum

enum class PaymentType(val type: String) {
    CREDIT_CARD("Credit Card"),  // 信用卡
    BANK_TRANSFER("Bank Transfer"),  // 银行转账
    CASH_ON_DELIVERY("Cash on Delivery") // 货到付款
}
