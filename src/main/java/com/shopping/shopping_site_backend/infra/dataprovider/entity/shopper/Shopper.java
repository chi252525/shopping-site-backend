package com.shopping.shopping_site_backend.infra.dataprovider.entity.shopper;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.shopping.shopping_site_backend.infra.constant.DateTimeConstant;
import com.shopping.shopping_site_backend.infra.dataprovider.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

/** 會員資料檔 */
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@DynamicUpdate
@Entity
@Table(name = "ec_shopper")
public class Shopper extends BaseEntity {

  /** 唯一序號 */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  /** 身分證 ID */
  @Column(name = "IDNo", columnDefinition = "VARCHAR(16)", nullable = false)
  private String idNo;

  /** 會員狀態 */
  @Column(columnDefinition = "boolean", nullable = false, updatable = false)
  private boolean enabled;

  /** 中文姓名 */
  @Column(columnDefinition = "VARCHAR(32)")
  private String name;

  @Column(columnDefinition = "VARCHAR(32)")
  private String email;

  /** 性別，英文代碼，F 為女性，M 為男性 */
  @Column(columnDefinition = "VARCHAR(8)")
  private String gender;

  /** 生日 */
  @JsonFormat(pattern = DateTimeConstant.DATE_FORMAT)
  @Column(columnDefinition = "DATE")
  private LocalDate birthDate;

  /** 更新時間 */
  @Column(columnDefinition = "DATETIME", nullable = false)
  private LocalDateTime updatedAt;
}
