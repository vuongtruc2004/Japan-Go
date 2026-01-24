package com.nass.infrastructure.entities.vocabulary;

import com.nass.infrastructure.entities.base.BaseEntity;
import jakarta.persistence.*;
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
@Table(name = "vocabulary_reading")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class VocabularyReadingEntity extends BaseEntity<Long> {
    @Column(name = "japanese_reading", nullable = false, unique = true)
    String japaneseReading;

    @Column(name = "latin_reading")
    String latinReading;

    @ManyToOne
    @JoinColumn(name = "vocabulary_id")
    VocabularyEntity vocabulary;

    @Builder.Default
    @OneToMany(mappedBy = "vocabularyReading")
    List<VocabularyMeaningEntity> vocabularyMeanings = new ArrayList<>();
}
