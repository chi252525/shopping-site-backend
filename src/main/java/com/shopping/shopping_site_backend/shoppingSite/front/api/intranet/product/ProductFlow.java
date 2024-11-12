package com.shopping.shopping_site_backend.shoppingSite.front.api.intranet.product;

import com.shopping.shopping_site_backend.shoppingSite.front.controller.intranet.product.model.ProductRequestV1_0;
import com.shopping.shopping_site_backend.shoppingSite.front.controller.intranet.product.model.ProductResponseV1_0;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class ProductFlow {


  public ProductResponseV1_0 execute(@Valid ProductRequestV1_0 request) {



  return new ProductResponseV1_0();
}}
