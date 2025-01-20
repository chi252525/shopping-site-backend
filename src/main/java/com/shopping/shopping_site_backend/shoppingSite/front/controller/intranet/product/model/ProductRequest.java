package com.shopping.shopping_site_backend.shoppingSite.front.controller.intranet.product.model;

import com.shopping.shopping_site_backend.shoppingSite.front.controller.intranet.category.model.CategoryRequest;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import java.time.LocalDateTime;
import lombok.Data;

@Data
public class ProductRequest {

  @Schema(description = "商家 ID", example = "1")
  private Long merchantId;

  @Schema(description = "產品名稱關鍵字", example = "Phone")
  @Size(max = 50, message = "名稱不能超過 50 字")
  private String name;

  @Schema(description = "產品編號", example = "A001")
  @Size(max = 50, message = "名稱不能超過 50 字")
  private String baseSku;

  @Schema(description = "第一層分類")
  private Long firstCategory;

  @Schema(description = "第二層分類")
  private Long secondCategory;

  @Schema(description = "第二層分類")
  private Long thirdCategory;

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

  @Schema(description = "是否已到貨", example = "true")
  private Boolean inStock;

  @Schema(description = "是否有顯示在前台", example = "true")
  private Boolean isShow;

  @Schema(description = "上架時間")
  private LocalDateTime startTime;

  @Schema(description = "下架時間")
  private LocalDateTime endTime;

  @Schema(name = "是否要庫存返還")
  private Boolean returnQuantityOnCancel;

  @Schema(name = "庫存警示數")
  private Integer alertThreshold;

}
