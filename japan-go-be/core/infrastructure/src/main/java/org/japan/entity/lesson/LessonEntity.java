package org.japan.entity.lesson;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.japan.constants.lesson.LessonTypeEnum;
import org.japan.entity.base.BaseEntity;
import org.japan.entity.common.FolderEntity;

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

    @ManyToOne
    @JoinColumn(name = "book_id")
    BookEntity book;

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
