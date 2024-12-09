package com.shopping.shopping_site_backend.infra.constant.enumconstant;

public enum ShippingType {
  STANDARD("Standard"),
  SEVENC2C("SEVENC2C"); //
  private String description;

  ShippingType(String description) {
    this.description = description;
  }
}
