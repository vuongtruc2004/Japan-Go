package com.nass.common.persistence;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Instant;

@MappedSuperclass
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseEntity<TKey> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    TKey id;

    @CreatedBy
    @Column(nullable = false, updatable = false, name = "created_by")
    String createdBy;

    @CreatedDate
    @Column(nullable = false, updatable = false, name = "created_time")
    Instant createdTime;

    @LastModifiedBy
    @Column(insertable = false, name = "modified_by")
    String modifiedBy;

    @LastModifiedDate
    @Column(insertable = false, name = "modified_time")
    Instant modifiedTime;
}
