package com.japan_go_be.infrastructure.entities.grammar;

import com.japan_go_be.infrastructure.entities.base.BaseEntity;
import com.japan_go_be.infrastructure.entities.lesson.GrammarLessonEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "grammar")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class GrammarEntity extends BaseEntity {
    @Column(name = "grammar_title")
    String grammarTitle;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "grammar_meaning_id")
    GrammarMeaningEntity grammarMeaning;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "grammar_structure_id")
    GrammarStructureEntity grammarStructure;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "grammar_example_id")
    GrammarExampleEntity grammarExample;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "grammar_note_id")
    GrammarNoteEntity grammarNote;

    @ManyToOne
    @JoinColumn(name = "grammar_lesson_id")
    GrammarLessonEntity grammarLesson;
}
