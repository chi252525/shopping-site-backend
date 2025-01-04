package com.shopping.shopping_site_backend.shoppingSite.front.controller.intranet.product.model;

import com.shopping.shopping_site_backend.infra.dataprovider.entity.product.Category;
import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;
import lombok.Data;

@Data
public class ProductResponse {

  @Schema(description = "產品 ID", example = "101")
  private Long id;

  @Schema(description = "產品名稱", example = "Phone X")
  private String name;

  @Schema(description = "產品編號", example = "Phone X")
  private String baseSku;

  @Schema(description = "單位價格", example = "999.99")
  private Double unitPrice;

  @Schema(description = "販售價格", example = "999.99")
  private Double salePrice;

  @Schema(description = "折扣價格", example = "899.99")
  private Double discountPrice;

  @Schema(description = "是否有庫存", example = "true")
  private Boolean inStock;

  @Schema(description = "是否有顯示在前台", example = "true")
  private Boolean isShow;

  @Schema(description = "開始販售時間")
  private LocalDateTime startTime;

  @Schema(description = "結束販售時間")
  private LocalDateTime endTime;

  @Schema(description = "第一層分類")
  private String firstCategory;

  @Schema(description = "第二層分類")
  private String secondCategory;

  @Schema(description = "商家 ID")
  private Long merchantId;

  @Schema(description = "版本號")
  private String versionId;
}
