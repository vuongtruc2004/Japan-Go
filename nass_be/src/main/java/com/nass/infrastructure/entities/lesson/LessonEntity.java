package com.nass.infrastructure.entities.lesson;

import com.nass.contract.enums.lesson.LessonTypeEnum;
import com.nass.infrastructure.entities.base.BaseEntity;
import com.nass.infrastructure.entities.common.FolderEntity;
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

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "grammar_lesson_id")
    GrammarLessonEntity grammarLesson;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "kanji_lesson_id")
    KanjiLessonEntity kanjiLesson;

    @ManyToOne
    @JoinColumn(name = "folder_id")
    FolderEntity folder;
}
