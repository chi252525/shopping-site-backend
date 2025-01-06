package com.shopping.shopping_site_backend.shoppingSite.front.controller.intranet.category;

import com.shopping.shopping_site_backend.shoppingSite.front.api.intranet.category.CategoryPresentation;
import com.shopping.shopping_site_backend.shoppingSite.front.controller.intranet.category.model.CategoryRequest;
import com.shopping.shopping_site_backend.shoppingSite.front.controller.intranet.category.model.CategoryResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "分類")
@RequiredArgsConstructor
@RestController
@RequestMapping("${intranet}/category")
public class CategoryController {

  private final CategoryPresentation categoryPresentation;

  // Create Category
  @Operation(
      tags = {"分類"},
      summary = "新增分類",
      description = "創建新的分類")
  @PostMapping("/create")
  public ResponseEntity<CategoryResponse> createCategory(
      @Parameter(required = true) @Valid @RequestBody CategoryRequest request) {
    CategoryResponse category = categoryPresentation.createCategory(request);
    return new ResponseEntity<>(category, HttpStatus.CREATED);
  }

  // Get Category by ID
  @Operation(
      tags = {"分類"},
      summary = "根據ID查詢分類",
      description = "根據分類ID獲取分類詳細信息")
  @GetMapping("/{id}/get")
  public ResponseEntity<CategoryResponse> getCategoryById(
      @Parameter(required = true) @PathVariable("id") Long id) {
    CategoryResponse category = categoryPresentation.getCategoryById(id);
    return category != null
        ? new ResponseEntity<>(category, HttpStatus.OK)
        : new ResponseEntity<>(HttpStatus.NOT_FOUND);
  }

  // Get All Categories
  @Operation(
      tags = {"分類"},
      summary = "查詢所有分類（分頁）")
  @GetMapping("/list")
  public ResponseEntity<List<CategoryResponse>> getAllCategories() {
    List<CategoryResponse> categories = categoryPresentation.query();
    return new ResponseEntity<>(categories, HttpStatus.OK);
  }

  // Update Category
  @Operation(
      tags = {"分類"},
      summary = "更新分類",
      description = "根據ID更新現有的分類")
  @PutMapping("/{id}/update")
  public ResponseEntity<CategoryResponse> updateCategory(
      @Parameter(required = true) @PathVariable("id") Long id,
      @Parameter(required = true) @Valid @RequestBody CategoryRequest request) {
    categoryPresentation.updateCategory(id, request);
    return new ResponseEntity<>(HttpStatus.OK);
  }

  // Delete Category
  @Operation(
      tags = {"分類"},
      summary = "刪除分類",
      description = "根據ID刪除指定的分類")
  @DeleteMapping("/{id}/delete")
  public ResponseEntity<Void> deleteCategory(
      @Parameter(required = true) @PathVariable("id") Long id) {
    categoryPresentation.deleteCategory(id);
    return new ResponseEntity<>(HttpStatus.OK);
  }
}
