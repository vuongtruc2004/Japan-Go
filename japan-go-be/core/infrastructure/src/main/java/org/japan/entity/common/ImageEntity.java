package org.japan.entity.common;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.japan.entity.base.BaseEntity;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "images")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ImageEntity extends BaseEntity {
    @Column(name = "img_path")
    String imgPath;

    @Column(name = "img_alt")
    String imgAlt;
}
