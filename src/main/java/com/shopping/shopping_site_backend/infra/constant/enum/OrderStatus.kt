package com.shopping.shopping_site_backend.infra.constant.enum

enum class OrderStatus(val status: String) {
    PENDING("Pending"),  // 待处理
    CONFIRMED("Confirmed"),  // 已确认
    SHIPPED("Shipped"),  // 已发货
    DELIVERED("Delivered"),  // 已送达
    CANCELED("Canceled") // 已取消
}
