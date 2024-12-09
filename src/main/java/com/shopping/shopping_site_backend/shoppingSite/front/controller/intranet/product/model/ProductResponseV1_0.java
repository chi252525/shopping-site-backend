package com.shopping.shopping_site_backend.shoppingSite.front.controller.intranet.product.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class ProductResponseV1_0 {

  @Schema(description = "產品 ID", example = "101")
  private Long id;

  @Schema(description = "產品名稱", example = "Phone X")
  private String name;

  @Schema(description = "單位價格", example = "999.99")
  private Double unitPrice;

  @Schema(description = "折扣價格", example = "899.99")
  private Double discountPrice;

  @Schema(description = "是否有庫存", example = "true")
  private Boolean inStock;
}
