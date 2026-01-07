package com.nass.infrastructure.entities.base;

import jakarta.persistence.MappedSuperclass;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@MappedSuperclass
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BaseCourseEntity<TKey> extends BaseEntity<TKey> {
    String title;
    String description;
}
