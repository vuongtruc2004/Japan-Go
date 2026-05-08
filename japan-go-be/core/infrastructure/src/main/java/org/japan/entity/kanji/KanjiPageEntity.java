package org.japan.entity.kanji;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.japan.entity.base.BaseEntity;
import org.japan.entity.lesson.KanjiLessonEntity;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "kanji_pages")
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
