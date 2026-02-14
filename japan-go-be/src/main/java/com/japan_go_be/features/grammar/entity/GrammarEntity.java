package com.japan_go_be.features.grammar.entity;

import com.japan_go_be.common.persistence.BaseEntity;
import com.japan_go_be.features.lesson.entity.GrammarLessonEntity;
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

    @Builder.Default
    @ManyToMany(mappedBy = "grammars")
    List<GrammarLessonEntity> grammarLessons = new ArrayList<>();
}
