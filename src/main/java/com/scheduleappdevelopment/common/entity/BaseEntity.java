package com.scheduleappdevelopment.common.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import java.time.LocalDateTime;

/**
 * BaseEntity: 모든 엔티티의 공통 필드(생성일, 수정일, 생성자, 수정자)를 관리하는 부모 클래스
 */
@Getter
@MappedSuperclass // 자식 엔티티들이 이 클래스의 필드를 DB 컬럼으로 인식하게 함
@EntityListeners(AuditingEntityListener.class) // 데이터 변화를 감시하여 아래 필드값을 자동으로 채워줌
public abstract class BaseEntity {

    @CreatedDate // 생성 시 자동으로 현재 시간 저장
    @Column(updatable = false) // 생성 후에는 수정 불가능하게 설정
    private LocalDateTime createdAt;

    @CreatedBy // 생성 시 자동으로 작성자 ID(AuditorAware에서 준 값) 저장
    @Column(updatable = false)
    private Long createdBy;

    @LastModifiedDate // 수정 시 자동으로 현재 시간 업데이트
    private LocalDateTime modifiedAt;

    @LastModifiedBy // 수정 시 자동으로 수정자 ID 저장
    private Long modifiedBy;
}