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
          ProductRequest request,
      Pageable pageable) {
    // 構建查詢條件
    Specification<Product> spec = Specification.where(null);

    if (request.getMerchantId() != null) {
      spec = spec.and(hasMerchantId(request.getMerchantId()));
    }
    if (request.getWholesalerId() != null) {
      spec = spec.and(hasWholesalerId(request.getWholesalerId()));
    }
    if (request.getFirstCategory() != null) {
      spec = spec.and(hasFirstCategoryId(request.getFirstCategory()));
    }
    if (request.getSecondCategory() != null) {
      spec = spec.and(hasSecondCategoryId(request.getSecondCategory()));
    }
    if (request.getThirdCategory()!= null) {
      spec = spec.and(hasThirdCategoryId(request.getThirdCategory()));
    }
    if (request.getBaseSku() != null) {
      spec = spec.and(baseSkuContains(request.getBaseSku()));
    }
    if (request.getName()!= null && !request.getName().isEmpty()) {
      spec = spec.and(nameContains(request.getName()));
    }
    if (request.getMaxPrice() != null && request.getMinPrice() != null) {
      spec = spec.and(priceBetween(request.getMinPrice(), request.getMaxPrice()));
    }
    if (request.getInStock() != null) {
      spec = spec.and(isInStock(request.getInStock()));
    }

    if (request.getIsShow() != null) {
      spec = spec.and(isShow(request.getIsShow()));
    }

    if (request.getIsSettled() != null) {
      spec = spec.and(isSettled(request.getIsSettled()));
    }

    if (request.getIsOld() != null) {
      spec = spec.and(isOld(request.getIsOld()));
    }

    if (request.getStartTime() != null && request.getEndTime() != null) {
      spec = spec.and(isAvailableBetween(request.getStartTime(), request.getEndTime() ));
    }
    return productRepository.findAll(spec, pageable);
  }
  private Specification<Product> hasThirdCategoryId(Long thirdCategory) {
    return (root, query, criteriaBuilder) ->
            criteriaBuilder.equal(root.get("thirdCategory").get("id"), thirdCategory);

  }
  private Specification<Product> hasSecondCategoryId(Long secondCategory) {
    return (root, query, criteriaBuilder) ->
            criteriaBuilder.equal(root.get("secondCategory").get("id"), secondCategory);

  }

  private Specification<Product> hasFirstCategoryId(Long firstCategoryId) {
    return (root, query, criteriaBuilder) ->
            criteriaBuilder.equal(root.get("firstCategory").get("id"), firstCategoryId);
  }

  public static Specification<Product> hasMerchantId(Long merchantId) {
    return (root, query, criteriaBuilder) ->
        criteriaBuilder.equal(root.get("merchant").get("id"), merchantId);
  }

  public static Specification<Product> hasWholesalerId(Long wholesalerId) {
    return (root, query, criteriaBuilder) ->
            criteriaBuilder.equal(root.get("wholesaler").get("id"), wholesalerId);
  }
  public static Specification<Product> isInStock(Boolean inStock) {
    return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("inStock"), inStock);
  }

  public static Specification<Product> isShow(Boolean isShow) {
    return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("isShow"), isShow);
  }

  public static Specification<Product> isSettled(Boolean isSettled) {
    return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("isSettled"), isSettled);
  }

  public static Specification<Product> isOld(Boolean isOld) {
    return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("isOld"), isOld);
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
