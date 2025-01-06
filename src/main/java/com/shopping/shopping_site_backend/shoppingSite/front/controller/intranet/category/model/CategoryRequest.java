package com.shopping.shopping_site_backend.shoppingSite.front.controller.intranet.category.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoryRequest {

  private Boolean enabled; // 是否啟用

  private String name; // 分類名稱

  private Long level; // 分類層級
}
