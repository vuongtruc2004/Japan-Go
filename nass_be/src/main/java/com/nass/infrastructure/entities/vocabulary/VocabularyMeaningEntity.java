package com.nass.infrastructure.entities.vocabulary;

import com.nass.infrastructure.entities.base.BaseEntity;
import com.nass.infrastructure.entities.common.SentenceEntity;
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
@Table(name = "vocabulary_meaning")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class VocabularyMeaningEntity extends BaseEntity<Long> {
    @Column(name = "japanese_meaning")
    String japaneseMeaning;

    @Column(name = "vietnamese_meaning")
    String vietnameseMeaning;

    @Column(name = "english_meaning")
    String englishMeaning;

    @ManyToOne
    @JoinColumn(name = "vocabulary_reading_id")
    VocabularyReadingEntity vocabularyReading;

    // examples sentence for each meaning
    @Builder.Default
    @OneToMany(mappedBy = "vocabularyMeaning")
    List<SentenceEntity> sentences = new ArrayList<>();
}
