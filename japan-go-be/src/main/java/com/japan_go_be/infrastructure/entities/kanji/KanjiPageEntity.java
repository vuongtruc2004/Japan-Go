package com.japan_go_be.infrastructure.entities.kanji;

import com.japan_go_be.infrastructure.entities.base.BaseEntity;
import com.japan_go_be.infrastructure.entities.lesson.KanjiLessonEntity;
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
    @OneToMany(mappedBy = "kanjiPage", cascade = CascadeType.ALL, orphanRemoval = true)
    List<VocabularyEntity> vocabularies = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "kanji_lesson_id")
    KanjiLessonEntity kanjiLesson;
}
