package com.nass.business.image.entity;

import com.nass.common.persistence.BaseEntity;
import jakarta.persistence.Column;
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
    @Column(name = "img_path")
    String imgPath;

    @Column(name = "img_alt")
    String imgAlt;
}
