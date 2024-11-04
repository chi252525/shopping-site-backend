package com.shopping.shopping_site_backend.infra.constant.enum

enum class PaymentStatus(val status: String) {
    PENDING("Pending"),  // 待支付
    COMPLETED("Completed"),  // 已完成
    FAILED("Failed"),  // 失败
    REFUNDED("Refunded") // 已退款
}
