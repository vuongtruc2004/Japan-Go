package org.japan.entity.vocabulary;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.japan.entity.base.BaseEntity;
import org.japan.entity.kanji.KanjiPageEntity;

import java.util.ArrayList;
import java.util.List;

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

    @Builder.Default
    @OneToMany(mappedBy = "vocabulary", cascade = CascadeType.ALL, orphanRemoval = true)
    List<SupportVocabularyEntity> supportVocabularies = new ArrayList<>();
}
