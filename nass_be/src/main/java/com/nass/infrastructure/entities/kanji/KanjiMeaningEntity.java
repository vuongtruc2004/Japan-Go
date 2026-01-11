package com.nass.infrastructure.entities.kanji;

import com.nass.infrastructure.entities.base.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "meaning")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class KanjiMeaningEntity extends BaseEntity<Long> {
    @Column(name = "reading_text", unique = true, nullable = false)
    String readingText;

    @Builder.Default
    @ManyToMany(mappedBy = "kanjiMeaningList")
    List<KanjiEntity> kanjiList = new ArrayList<>();
}
