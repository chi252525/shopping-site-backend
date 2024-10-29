package com.shopping.shopping_site_backend.infra.dataprovider.entity.member;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.shopping.shopping_site_backend.infra.constant.DateTimeConstant;
import com.shopping.shopping_site_backend.infra.sys.spring.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

import java.time.LocalDate;
import java.time.LocalDateTime;

/** 會員資料檔 */
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@DynamicUpdate
@Entity
@Table(name = "Member")
public class Member extends BaseEntity {

    /** 唯一序號 */
    @Id
    @Column(columnDefinition = "VARCHAR(128)", nullable = false, updatable = false)
    private String id;

    /** 身分證 ID */
    @Column(name = "IDNo", columnDefinition = "VARCHAR(16)", nullable = false)
    private String idNo;

    /** 會員狀態 */
    @Column(columnDefinition = "VARCHAR(16)", nullable = false, updatable = false)
    private String status;

    /** 中文姓名 */
    @Column(columnDefinition = "NVARCHAR(32)")
    private String name;

    @Column(columnDefinition = "NVARCHAR(32)")
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
