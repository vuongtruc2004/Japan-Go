package com.nass.infrastructure.entities.lesson;

import com.nass.infrastructure.entities.base.BaseEntity;
import com.nass.infrastructure.entities.grammar.GrammarEntity;
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
public class GrammarLessonEntity extends BaseEntity<Integer> {
    @Column(name = "grammar_lesson_title", nullable = false)
    String grammarLessonTitle;

    @Builder.Default
    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(name = "grammar_lesson_grammar",
            joinColumns = @JoinColumn(name = "grammar_lesson_id"),
            inverseJoinColumns = @JoinColumn(name = "grammar_id"))
    List<GrammarEntity> grammars = new ArrayList<>();

    @OneToOne(mappedBy = "grammarLesson", cascade = CascadeType.ALL, orphanRemoval = true)
    LessonEntity lesson;
}
