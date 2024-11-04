package com.shopping.shopping_site_backend.infra.constant.enum

enum class ShippingStatus (status: kotlin.String) {
    PENDING("Pending"),
    SHIPPED("Shipped"),
    DELIVERED("Delivered"),
    CANCELED("Canceled"),
    RETURNED("Returned");

    private val status: kotlin.String

    init {
        /*~~nxwbkr~~*/this.status = status
    }

    fun getStatus(): kotlin.String {
        return status
    }
}