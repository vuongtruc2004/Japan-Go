package com.japan_go_be.infrastructure.entities.lesson;

import com.japan_go_be.contract.constants.lesson.LessonTypeEnum;
import com.japan_go_be.infrastructure.entities.base.BaseEntity;
import com.japan_go_be.infrastructure.entities.common.FolderEntity;
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
@Table(name = "lessons")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class LessonEntity extends BaseEntity {
    @Column(name = "lesson_name", nullable = false)
    String lessonName;

    String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "lesson_type")
    LessonTypeEnum lessonType;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "grammar_lesson_id")
    GrammarLessonEntity grammarLesson;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "kanji_lesson_id")
    KanjiLessonEntity kanjiLesson;

    @Builder.Default
    @ManyToMany(mappedBy = "lessons")
    List<FolderEntity> folders = new ArrayList<>();
}
