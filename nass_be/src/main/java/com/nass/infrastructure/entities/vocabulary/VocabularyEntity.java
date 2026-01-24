package com.nass.infrastructure.entities.vocabulary;

import com.nass.infrastructure.entities.base.BaseEntity;
import com.nass.infrastructure.entities.kanji.KanjiPageEntity;
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
@Table(name = "vocabulary")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class VocabularyEntity extends BaseEntity<Long> {
    @Column(nullable = false, unique = true)
    String japanese;

    @Column(name = "sino_vietnamese")
    String sinoVietnamese;

    @Builder.Default
    @ManyToMany
    @JoinTable(name = "vocabulary_vocabulary_type",
            joinColumns = @JoinColumn(name = "vocabulary_id"),
            inverseJoinColumns = @JoinColumn(name = "vocabulary_type_id"))
    List<VocabularyTypeEntity> vocabularyTypes = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "vocabulary")
    List<VocabularyReadingEntity> vocabularyReadings = new ArrayList<>();

    @Builder.Default
    @ManyToMany(mappedBy = "vocabularies")
    List<KanjiPageEntity> kanjiPages = new ArrayList<>();
}
