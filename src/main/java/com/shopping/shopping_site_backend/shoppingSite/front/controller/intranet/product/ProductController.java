package com.shopping.shopping_site_backend.shoppingSite.front.controller.intranet.product;

import com.shopping.shopping_site_backend.shoppingSite.front.api.intranet.product.ProductPresentation;
import com.shopping.shopping_site_backend.shoppingSite.front.controller.intranet.product.model.ProductRequest;
import com.shopping.shopping_site_backend.shoppingSite.front.controller.intranet.product.model.ProductResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@Tag(name = "產品")
@RequiredArgsConstructor
@RestController
@RequestMapping("${intranet}/product")
public class ProductController {

  private final ProductPresentation productPresentation;

  // Create Product
  @Operation(
      tags = {"產品"},
      summary = "新增產品",
      description = "創建新的產品")
  @PostMapping("/create")
  public ResponseEntity<ProductResponse> createProduct(
      @Parameter(required = true) @Valid @RequestBody ProductRequest request) {
    ProductResponse product = productPresentation.createProduct(request);
    return new ResponseEntity<>(product, HttpStatus.CREATED);
  }

  // Get Product by ID
  @Operation(
      tags = {"產品"},
      summary = "根據ID查詢產品",
      description = "根據產品ID獲取產品詳細信息")
  @GetMapping("/{id}/get")
  public ResponseEntity<ProductResponse> getProductById(
      @Parameter(required = true) @PathVariable("id") String id) {
    ProductResponse product = productPresentation.getProductById(Long.valueOf(id));
    return product != null
        ? new ResponseEntity<>(product, HttpStatus.OK)
        : new ResponseEntity<>(HttpStatus.NOT_FOUND);
  }

  // Get All Products
  @Operation(
      tags = {"產品"},
      summary = "查詢所有產品（分頁）",
      description = "獲取所有產品的列表，支持條件查詢與分頁")
  @GetMapping("/list")
  public ResponseEntity<Page<ProductResponse>> getAllProducts(
      ProductRequest request,
      @PageableDefault(size = 10, sort = "name", direction = Sort.Direction.ASC)
          Pageable pageable) {
    Page<ProductResponse> products = productPresentation.query(request, pageable);
    return new ResponseEntity<>(products, HttpStatus.OK);
  }

  // Update Product
  @Operation(
      tags = {"產品"},
      summary = "更新產品",
      description = "根據ID更新現有的產品")
  @PutMapping("/{id}/update")
  public ResponseEntity<ProductResponse> updateProduct(
      @Parameter(required = true) @PathVariable("id") Long id,
      @Parameter(required = true) @Valid @RequestBody ProductRequest request) {
    productPresentation.updateProduct(id, request);
    return new ResponseEntity<>(HttpStatus.OK);
  }

  // Delete Product
  @Operation(
      tags = {"產品"},
      summary = "刪除產品",
      description = "根據ID刪除指定的產品")
  @DeleteMapping("/{id}/delete")
  public ResponseEntity<Void> deleteProduct(
      @Parameter(required = true) @PathVariable("id") Long id) {
    productPresentation.deleteProduct(id);
    return new ResponseEntity<>(HttpStatus.OK);
  }
}
