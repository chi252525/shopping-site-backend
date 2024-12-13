package com.shopping.shopping_site_backend.infra.dataprovider.entity;

import jakarta.persistence.Column;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import org.springframework.security.core.context.SecurityContextHolder;

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

  @PrePersist
  protected void onCreate() {
    this.createdAt = LocalDateTime.now();
    this.updatedAt = LocalDateTime.now();
    this.createUserId = getCurrentUserId(); // 設置創建用戶
    this.updateUserId = getCurrentUserId(); // 初始設置更新用戶
  }

  @PreUpdate
  protected void onUpdate() {
    this.updatedAt = LocalDateTime.now();
    this.updateUserId = getCurrentUserId(); // 設置更新用戶
  }

  // 自定義方法獲取當前用戶 ID
  private Long getCurrentUserId() {
    // 模擬從上下文中獲取當前用戶 ID
    // 實際實現可基於 Spring Security 或其他身份驗證機制
    return SecurityContextHolder.getContext().getAuthentication() != null
            ? Long.valueOf(SecurityContextHolder.getContext().getAuthentication().getName())
            : null;
  }
}
