package com.codingrecipe.board.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Getter
public class BaseEntity {
    @CreationTimestamp // 생성됐을때 시간을 만들어주는 부분
    @Column(updatable = false)
    private LocalDateTime createdTime;

    @UpdateTimestamp // 업데이트 됐을때 만들어주는부분
    @Column(insertable = false)
    private LocalDateTime updatedTime;

}
