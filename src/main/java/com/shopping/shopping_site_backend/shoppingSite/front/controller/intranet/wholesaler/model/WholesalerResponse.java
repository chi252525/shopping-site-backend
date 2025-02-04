package com.shopping.shopping_site_backend.shoppingSite.front.controller.intranet.wholesaler.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WholesalerResponse {

  private String name;

  private Boolean enabled;

  private String address;

  private String from;
}