package com.shopping.shopping_site_backend.shoppingSite.front.api.intranet.product;

import com.shopping.shopping_site_backend.infra.dataprovider.entity.product.Product;
import com.shopping.shopping_site_backend.infra.sys.spring.repository.ProductRepository;
import com.shopping.shopping_site_backend.shoppingSite.front.controller.intranet.product.model.ProductRequest;
import java.time.LocalDateTime;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class ProductFlow {

  private final ProductRepository productRepository;

  public Page<Product> query(
      Long merchantId,
      String name,
      String baseSku,
      Double minPrice,
      Double maxPrice,
      Boolean inStock,
      LocalDateTime startTime,
      LocalDateTime endTime,
      Pageable pageable) {
    // 構建查詢條件
    Specification<Product> spec = Specification.where(null);

    if (merchantId != null) {
      spec = spec.and(hasMerchantId(merchantId));
    }

    if (baseSku != null) {
      spec = spec.and(baseSkuContains(baseSku));
    }
    if (name != null && !name.isEmpty()) {
      spec = spec.and(nameContains(name));
    }
    if (minPrice != null && maxPrice != null) {
      spec = spec.and(priceBetween(minPrice, maxPrice));
    }
    if (inStock != null) {
      spec = spec.and(isInStock(inStock));
    }
    if (startTime != null && endTime != null) {
      spec = spec.and(isAvailableBetween(startTime, endTime));
    }
    return productRepository.findAll(spec, pageable);
  }

  public static Specification<Product> hasMerchantId(Long merchantId) {
    return (root, query, criteriaBuilder) ->
        criteriaBuilder.equal(root.get("merchant").get("id"), merchantId);
  }

  public static Specification<Product> isInStock(Boolean inStock) {
    return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("inStock"), inStock);
  }

  public static Specification<Product> nameContains(String name) {
    return (root, query, criteriaBuilder) ->
        criteriaBuilder.like(root.get("name"), "%" + name + "%");
  }

  public static Specification<Product> baseSkuContains(String baseSku) {
    return (root, query, criteriaBuilder) ->
        criteriaBuilder.like(root.get("baseSku"), "%" + baseSku + "%");
  }

  public static Specification<Product> priceBetween(Double minPrice, Double maxPrice) {
    return (root, query, criteriaBuilder) ->
        criteriaBuilder.between(root.get("unitPrice"), minPrice, maxPrice);
  }

  public static Specification<Product> isAvailableBetween(
      LocalDateTime startTime, LocalDateTime endTime) {
    return (root, query, criteriaBuilder) ->
        criteriaBuilder.and(
            criteriaBuilder.greaterThanOrEqualTo(root.get("availableStartTime"), startTime),
            criteriaBuilder.lessThanOrEqualTo(root.get("availableEndTime"), endTime));
  }

  public void deleteByProductId(Long id) {
    Optional<Product> productOptional = productRepository.findById(id);
    if (!productOptional.isPresent()) {
      // TODO throw exception 404
      throw new RuntimeException();
    }
    Product product = productOptional.get();
    productRepository.delete(product);
  }

  public Product getProductById(Long id) {
    Optional<Product> productOptional = productRepository.findById(id);
    if (!productOptional.isPresent()) {
      // TODO throw exception 404
      throw new RuntimeException();
    }
    return productOptional.get();
  }

  public void updateProduct(Long id, ProductRequest request) {
    Optional<Product> productOptional = productRepository.findById(id);
    if (!productOptional.isPresent()) {
      throw new RuntimeException();
    }
    productRepository.save(toProduct(request, productOptional.get()));
  }

  private Product toProduct(ProductRequest request, Product product) {
    if (request == null) {
      return null;
    }
    product.setName(request.getName());
    product.setBaseSku(request.getBaseSku());
    product.setUnitPrice(request.getUnitPrice());
    product.setSalePrice(request.getSalePrice());
    product.setDiscountPrice(request.getDiscountPrice());
    product.setInStock(request.getInStock());
    product.setAvailableStartTime(request.getStartTime());
    product.setAvailableEndTime(request.getEndTime());
    return product;
  }

  public Product createProduct(ProductRequest request) {
    return productRepository.save(toProduct(request, new Product()));
  }
}
