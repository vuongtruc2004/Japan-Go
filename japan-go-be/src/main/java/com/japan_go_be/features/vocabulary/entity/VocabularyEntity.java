package com.japan_go_be.features.vocabulary.entity;

import com.japan_go_be.common.persistence.BaseEntity;
import com.japan_go_be.features.kanji.entity.KanjiPageEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "vocabulary")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class VocabularyEntity extends BaseEntity {
    @Column(nullable = false)
    String japanese;

    @Column(nullable = false)
    String reading;

    String meaning;

    String note;

    @ManyToOne
    @JoinColumn(name = "kanji_page_id")
    KanjiPageEntity kanjiPage;
}
