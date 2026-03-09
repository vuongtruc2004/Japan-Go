package com.japan_go_be.infrastructure.entities.common;

import com.japan_go_be.infrastructure.entities.base.BaseEntity;
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
public class ImageEntity extends BaseEntity {
    @Column(name = "img_path")
    String imgPath;

    @Column(name = "img_alt")
    String imgAlt;
}
