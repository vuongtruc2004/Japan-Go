package com.nass.infrastructure.entities.grammar;

import com.nass.infrastructure.entities.base.BaseEntity;
import com.nass.infrastructure.entities.lesson.GrammarLessonEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "grammar")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class GrammarEntity extends BaseEntity<Integer> {
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

    @Builder.Default
    @ManyToMany(mappedBy = "grammars")
    List<GrammarLessonEntity> grammarLessons = new ArrayList<>();
}
