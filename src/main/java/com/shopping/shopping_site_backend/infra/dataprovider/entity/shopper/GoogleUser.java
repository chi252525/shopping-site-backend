package com.shopping.shopping_site_backend.infra.dataprovider.entity.shopper;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.shopping.shopping_site_backend.infra.dataprovider.entity.BaseEntity;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.DynamicUpdate;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@DynamicUpdate
@Entity
public class GoogleUser extends BaseEntity {
  @JsonProperty("id")
  private Long id;

  @JsonProperty("email")
  private String email;

  @JsonProperty("name")
  private String name;
}
