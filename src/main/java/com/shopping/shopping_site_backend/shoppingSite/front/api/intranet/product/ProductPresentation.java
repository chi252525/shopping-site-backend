package com.shopping.shopping_site_backend.shoppingSite.front.api.intranet.product;

import com.shopping.shopping_site_backend.infra.dataprovider.entity.product.Product;
import com.shopping.shopping_site_backend.shoppingSite.front.controller.intranet.product.model.ProductRequestV1_0;
import com.shopping.shopping_site_backend.shoppingSite.front.controller.intranet.product.model.ProductResponseV1_0;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class ProductPresentation {
  private final ProductFlow productFlow;

  public Page<ProductResponseV1_0> query(ProductRequestV1_0 request, Pageable pageable) {
    Page<Product> products =
        productFlow.query(
            request.getMerchantId(),
            request.getName(),
            request.getMinPrice(),
            request.getMaxPrice(),
            request.getInStock(),
            request.getStartTime(),
            request.getEndTime(),
            pageable);

    // 將結果轉換為 Response DTO
    return products.map(this::toResponse);
  }

  private ProductResponseV1_0 toResponse(Product product) {
    ProductResponseV1_0 response = new ProductResponseV1_0();
    response.setId(product.getId());
    response.setName(product.getName());
    response.setUnitPrice(product.getUnitPrice());
    response.setDiscountPrice(product.getDiscountPrice());
    response.setInStock(product.getInStock());
    return response;
  }

  public void updateProduct(Long id, @Valid ProductRequestV1_0 request) {
    productFlow.updateProduct(id, request);
  }

  public void deleteProduct(Long id) {
    productFlow.deleteByProductId(id);
  }

  public ProductResponseV1_0 getProductById(Long id) {
    Product product = productFlow.getProductById(id);
    return toResponse(product);
  }

  public ProductResponseV1_0 createProduct(@Valid ProductRequestV1_0 request) {
    Product product = productFlow.createProduct(request);
    return toResponse(product);
  }
}
