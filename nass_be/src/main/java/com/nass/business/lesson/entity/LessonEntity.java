package com.nass.business.lesson.entity;

import com.nass.business.folder.entity.FolderEntity;
import com.nass.business.lesson.constant.LessonTypeEnum;
import com.nass.common.persistence.BaseEntity;
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

    @Builder.Default
    @ManyToMany(mappedBy = "lessons")
    List<FolderEntity> folders = new ArrayList<>();
}
