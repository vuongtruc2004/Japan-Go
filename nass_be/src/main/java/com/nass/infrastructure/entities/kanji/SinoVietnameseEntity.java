package com.nass.infrastructure.entities.kanji;

import com.nass.infrastructure.entities.base.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "sino_vietnamese")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SinoVietnameseEntity extends BaseEntity<Long> {
    String sinoVietnamese;

    @ManyToOne
    @JoinColumn(name = "kanji_id")
    KanjiEntity kanji;
}
