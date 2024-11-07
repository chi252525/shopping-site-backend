package com.shopping.shopping_site_backend.infra.constant.enumconstant;

public enum ShippingType {
    STANDARD("Standard");  // 标准运输
    private String description;
    ShippingType(String description) {
        this.description = description;
    }
}
