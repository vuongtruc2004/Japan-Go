package com.nass.infrastructure.entities.kanji;

import com.nass.infrastructure.entities.base.BaseEntity;
import jakarta.persistence.*;
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
    @Column(name = "sino_vietnamese")
    String sinoVietnamese;

    @Column(name = "priority_level")
    Integer priorityLevel;

    @ManyToOne
    @JoinColumn(name = "kanji_id")
    KanjiEntity kanji;
}
