package org.japan.entity.base;

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
public abstract class BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(nullable = false, updatable = false, name = "created_time")
    Instant createdTime;

    @Column(insertable = false, name = "modified_time")
    Instant modifiedTime;

    @PrePersist
    void prePersist() {
        createdTime = Instant.now();
    }

    @PreUpdate
    void preUpdate() {
        modifiedTime = Instant.now();
    }
}
