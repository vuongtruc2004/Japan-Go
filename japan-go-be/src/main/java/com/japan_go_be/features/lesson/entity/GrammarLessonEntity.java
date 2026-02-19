package com.japan_go_be.features.lesson.entity;

import com.japan_go_be.common.persistence.BaseEntity;
import com.japan_go_be.features.grammar.entity.GrammarEntity;
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
@Table(name = "grammar_lesson")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class GrammarLessonEntity extends BaseEntity {
    @Column(name = "grammar_lesson_title", nullable = false)
    String grammarLessonTitle;

    @Builder.Default
    @OneToMany(mappedBy = "grammarLesson", cascade = CascadeType.ALL, orphanRemoval = true)
    List<GrammarEntity> grammars = new ArrayList<>();

    @OneToOne(mappedBy = "grammarLesson", cascade = CascadeType.ALL, orphanRemoval = true)
    LessonEntity lesson;
}
