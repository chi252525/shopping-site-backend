package com.shopping.shopping_site_backend.shoppingSite.front.controller.intranet.product.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import java.time.LocalDateTime;
import lombok.Data;

@Data
public class ProductRequestV1_0 {

  @Schema(description = "商家 ID", example = "1")
  private Long merchantId;

  @Schema(description = "產品名稱關鍵字", example = "Phone")
  @Size(max = 50, message = "名稱不能超過 50 字")
  private String name;

  @Schema(description = "產品編號", example = "A001")
  @Size(max = 50, message = "名稱不能超過 50 字")
  private String baseSku;

  @Schema(description = "最小價格", example = "100")
  @Min(value = 0, message = "價格不能為負")
  private Double minPrice;

  @Schema(description = "最大價格", example = "1000")
  @Min(value = 0, message = "價格不能為負")
  private Double maxPrice;

  @Schema(description = "價格", example = "1000")
  @Min(value = 0, message = "價格不能為負")
  private Double unitPrice;

  @Schema(description = "販售價格", example = "1000")
  @Min(value = 0, message = "價格不能為負")
  private Double salePrice;

  @Schema(description = "優惠價格", example = "1000")
  @Min(value = 0, message = "價格不能為負")
  private Double discountPrice;

  @Schema(description = "是否有庫存", example = "true")
  private Boolean inStock;

  @Schema(description = "開始販售時間")
  private LocalDateTime startTime;

  @Schema(description = "結束販售時間")
  private LocalDateTime endTime;

  @Schema(description = "頁碼")
  private int page;

  @Schema(description = "個數")
  private int size;
}
