package com.japan_go_be.infrastructure.entities.lesson;

import com.japan_go_be.infrastructure.entities.base.BaseEntity;
import com.japan_go_be.infrastructure.entities.grammar.GrammarEntity;
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
@Table(name = "grammar_lessons")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class GrammarLessonEntity extends BaseEntity {
    @Builder.Default
    @OneToMany(mappedBy = "grammarLesson", cascade = CascadeType.ALL, orphanRemoval = true)
    List<GrammarEntity> grammars = new ArrayList<>();

    @OneToOne(mappedBy = "grammarLesson", cascade = CascadeType.ALL, orphanRemoval = true)
    LessonEntity lesson;
}
