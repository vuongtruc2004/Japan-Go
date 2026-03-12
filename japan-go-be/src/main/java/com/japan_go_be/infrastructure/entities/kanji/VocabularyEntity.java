package com.japan_go_be.infrastructure.entities.kanji;

import com.japan_go_be.infrastructure.entities.base.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "vocabularies")
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
