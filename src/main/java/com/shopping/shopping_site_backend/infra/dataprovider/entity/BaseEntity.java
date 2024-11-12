package com.shopping.shopping_site_backend.infra.dataprovider.entity;

import jakarta.persistence.Column;
import java.time.LocalDateTime;

public class BaseEntity {
  @Column(name = "created_at", updatable = false)
  private LocalDateTime createdAt; // 创建时间

  @Column(name = "updated_at")
  private LocalDateTime updatedAt; // 更新时间

  @Column(name = "create_user_id", updatable = false)
  private Long createUserId; // ID of the user who created the record

  @Column(name = "update_user_id")
  private Long updateUserId; // ID of the user who last updated the record
}
