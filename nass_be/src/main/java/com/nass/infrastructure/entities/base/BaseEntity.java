package com.nass.infrastructure.entities.base;

import com.nass.contract.constants.DefaultMessage;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.time.Instant;

@MappedSuperclass
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public abstract class BaseEntity<TKey> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    TKey id;

    @Column(nullable = false, updatable = false)
    String createdBy;

    @Column(nullable = false, updatable = false)
    Instant createdTime;

    String modifiedBy;
    Instant modifiedTime;

    @PrePersist
    protected void prePersist() {
        if (createdBy == null) {
            createdBy = DefaultMessage.DEFAULT_CREATED_BY;
        }
        createdTime = Instant.now();
    }

    @PreUpdate
    protected void preUpdate() {
        modifiedTime = Instant.now();
    }
}
