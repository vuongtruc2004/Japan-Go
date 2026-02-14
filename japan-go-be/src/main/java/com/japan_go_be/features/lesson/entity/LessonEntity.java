package com.japan_go_be.features.lesson.entity;

import com.japan_go_be.common.persistence.BaseEntity;
import com.japan_go_be.features.folder.entity.FolderEntity;
import com.japan_go_be.features.lesson.constant.LessonTypeEnum;
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
@Table(name = "lesson")
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
