package com.shopping.shopping_site_backend.shoppingSite.front.api.intranet.product;

import com.shopping.shopping_site_backend.shoppingSite.front.controller.intranet.product.model.ProductRequestV1_0;
import com.shopping.shopping_site_backend.shoppingSite.front.controller.intranet.product.model.ProductResponseV1_0;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class ProductPresentation {
  private final ProductFlow productFlow;

  public ProductPresentation(ProductFlow productFlow) {
    this.productFlow = productFlow;
  }

  public List<ProductResponseV1_0> presentV1_0(@Valid ProductRequestV1_0 request) {
    return (List<ProductResponseV1_0>) this.productFlow.execute(request);
  }

  public ProductResponseV1_0 updateProduct(Long id, @Valid ProductRequestV1_0 request) {
    return null;
  }

  public boolean deleteProduct(Long id) {
    return false;
  }


  public ProductResponseV1_0 getProductById(Long id) {
    return null;
  }

  public ProductResponseV1_0 createProduct(@Valid ProductRequestV1_0 request) {
    return null;
  }
}
