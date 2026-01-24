package com.nass.infrastructure.entities.lesson;

import com.nass.contract.enums.LessonTypeEnum;
import com.nass.infrastructure.entities.base.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "lesson")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class LessonEntity extends BaseEntity<Integer> {
    // like a file name
    @Column(name = "lesson_name", nullable = false)
    String lessonName;

    @Enumerated(EnumType.STRING)
    @Column(name = "lesson_type")
    LessonTypeEnum lessonType;

    @OneToOne
    @JoinColumn(name = "grammar_lesson_id")
    GrammarLessonEntity grammarLesson;

    @OneToOne
    @JoinColumn(name = "kanji_lesson_id")
    KanjiLessonEntity kanjiLesson;
}
