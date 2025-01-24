package com.shopping.shopping_site_backend.shoppingSite.front.api.intranet.product;

import com.shopping.shopping_site_backend.infra.dataprovider.entity.product.Product;
import com.shopping.shopping_site_backend.shoppingSite.front.controller.intranet.product.model.CategoryResponse;
import com.shopping.shopping_site_backend.shoppingSite.front.controller.intranet.product.model.ProductRequest;
import com.shopping.shopping_site_backend.shoppingSite.front.controller.intranet.product.model.ProductResponse;
import com.shopping.shopping_site_backend.shoppingSite.front.controller.intranet.product.model.WholesalerResponse;
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

  public Page<ProductResponse> query(ProductRequest request, Pageable pageable) {
    Page<Product> products =
        productFlow.query(
            request.getMerchantId(),
            request.getName(),
            request.getBaseSku(),
            request.getMinPrice(),
            request.getMaxPrice(),
            request.getInStock(),
            request.getStartTime(),
            request.getEndTime(),
            pageable);

    // 將結果轉換為 Response DTO
    return products.map(this::toResponse);
  }

  // TODO Refactor
  private ProductResponse toResponse(Product product) {
    ProductResponse response = new ProductResponse();
    response.setId(product.getId());
    response.setName(product.getName());
    response.setBaseSku(product.getBaseSku());
    response.setUnitPrice(product.getUnitPrice());
    response.setSalePrice(product.getSalePrice());
    response.setInStock(product.getInStock());
    response.setIsShow(product.getIsShow());
    response.setDiscountPrice(product.getDiscountPrice());
    response.setStartTime(product.getAvailableStartTime());
    response.setEndTime(product.getAvailableEndTime());

    response.setFirstCategory(
        new CategoryResponse(
            product.getFirstCategory().getName(), product.getFirstCategory().getId().toString()));
    response.setSecondCategory(
        new CategoryResponse(
            product.getSecondCategory().getName(), product.getSecondCategory().getId().toString()));
    response.setThirdCategory(new CategoryResponse(
        product.getThirdCategory().getName(), product.getThirdCategory().getId().toString()));
    response.setVersionId(product.getVersionId());
    response.setMerchantId(product.getMerchant().getId());
    response.setDescription(product.getDescription());
    response.setEstimatedTotalProfit(product.getEstimatedTotalProfit());
    response.setIsSettled(product.getIsSettled());
    response.setIsOld(product.getIsOld());
    response.setWholesaler(new WholesalerResponse(product.getWholesaler().getName(),product.getWholesaler().getId().toString()));
    response.setEstimatedProfit(product.getEstimatedProfit());
    response.setTotalCost(product.getTotalCost());
    return response;
  }

  public void updateProduct(Long id, @Valid ProductRequest request) {
    productFlow.updateProduct(id, request);
  }

  public void deleteProduct(Long id) {
    productFlow.deleteByProductId(id);
  }

  public ProductResponse getProductById(Long id) {
    Product product = productFlow.getProductById(id);
    return toResponse(product);
  }

  public ProductResponse createProduct(@Valid ProductRequest request) {
    Product product = productFlow.createProduct(request);
    return toResponse(product);
  }
}
