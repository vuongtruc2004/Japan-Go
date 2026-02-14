package com.japan_go_be.features.kanji.entity;

import com.japan_go_be.common.persistence.BaseEntity;
import com.japan_go_be.features.lesson.entity.KanjiLessonEntity;
import com.japan_go_be.features.vocabulary.entity.VocabularyEntity;
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
@Table(name = "kanji_page")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class KanjiPageEntity extends BaseEntity {
    @ManyToOne
    @JoinColumn(name = "main_kanji_id")
    KanjiEntity mainKanji;

    @Builder.Default
    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(name = "kanji_page_vocabulary",
            joinColumns = @JoinColumn(name = "kanji_page_id"),
            inverseJoinColumns = @JoinColumn(name = "vocabulary_id"))
    List<VocabularyEntity> vocabularies = new ArrayList<>();

    @Builder.Default
    @ManyToMany(mappedBy = "kanjiPages")
    List<KanjiLessonEntity> kanjiLessons = new ArrayList<>();
}
