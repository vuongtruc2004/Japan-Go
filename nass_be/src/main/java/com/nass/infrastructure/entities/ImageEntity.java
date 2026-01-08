package com.nass.infrastructure.entities;

import com.nass.infrastructure.entities.base.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "image")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ImageEntity extends BaseEntity<Long> {
    String imgPath;
    String imgAlt;
}
