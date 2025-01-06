package com.shopping.shopping_site_backend.shoppingSite.front.api.intranet.category;

import com.shopping.shopping_site_backend.infra.dataprovider.entity.product.Category;
import com.shopping.shopping_site_backend.infra.sys.spring.repository.CategoryRepository;
import com.shopping.shopping_site_backend.shoppingSite.front.controller.intranet.category.model.CategoryRequest;
import com.shopping.shopping_site_backend.shoppingSite.front.controller.intranet.category.model.CategoryResponse;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CategoryPresentation {

  private final CategoryRepository categoryRepository;

  // 創建分類
  public CategoryResponse createCategory(CategoryRequest request) {
    Category category = new Category();
    category.setEnabled(request.getEnabled());
    category.setName(request.getName());
    category.setLevel(request.getLevel());
    category = categoryRepository.save(category);
    return toCategoryResponse(category);
  }

  // 根據 ID 查詢分類
  public CategoryResponse getCategoryById(Long id) {
    Category category = categoryRepository.findById(id).orElse(null);
    return category != null ? toCategoryResponse(category) : null;
  }

  // 查詢所有分類（分頁）
  public List<CategoryResponse> query() {
    List<Category> categories = categoryRepository.findByEnabledTrue();
    return categories.stream().map(this::toCategoryResponse).toList();
  }

  // 更新分類
  public void updateCategory(Long id, CategoryRequest request) {
    Category category = categoryRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("分類不存在"));
    category.setEnabled(request.getEnabled());
    category.setName(request.getName());
    category.setLevel(request.getLevel());
    categoryRepository.save(category);
  }

  // 刪除分類
  public void deleteCategory(Long id) {
    categoryRepository.deleteById(id);
  }

  // 將 Category 轉換為 CategoryResponse
  private CategoryResponse toCategoryResponse(Category category) {
    CategoryResponse response = new CategoryResponse();
    response.setId(category.getId());
    response.setEnabled(category.getEnabled());
    response.setName(category.getName());
    response.setLevel(category.getLevel());
    return response;
  }
}
