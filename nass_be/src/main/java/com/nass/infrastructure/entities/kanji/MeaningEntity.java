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
@Table(name = "meaning")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MeaningEntity extends BaseEntity<Long> {
    String meaning;

    @ManyToOne
    @JoinColumn(name = "kanji_id")
    KanjiEntity kanji;
}
