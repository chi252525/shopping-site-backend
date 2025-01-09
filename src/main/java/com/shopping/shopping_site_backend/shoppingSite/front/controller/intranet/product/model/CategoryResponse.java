package com.shopping.shopping_site_backend.shoppingSite.front.controller.intranet.product.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Data
public class CategoryResponse {
  private String label;
  private String value;


  public CategoryResponse(String name, String id) {
    this.label = name;
    this.value = id;
  }
}
