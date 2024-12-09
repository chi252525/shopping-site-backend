package com.shopping.shopping_site_backend.infra.dataprovider.entity.shopper;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
public class GoogleUser {
  @JsonProperty("id")
  private String id;

  @JsonProperty("email")
  private String email;

  @JsonProperty("verified_email")
  private boolean verifiedEmail;

  @JsonProperty("name")
  private String name;

  @JsonProperty("given_name")
  private String givenName;

  @JsonProperty("family_name")
  private String familyName;

  @JsonProperty("picture")
  private String picture;

  @JsonProperty("locale")
  private String locale;
  // No-argument constructor
  public GoogleUser() {}

  public GoogleUser(Long id, String name, String email) {
    this.id = id.toString();
    this.name = name;
    this.email = email;
  }
}
