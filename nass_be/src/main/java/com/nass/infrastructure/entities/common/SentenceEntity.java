package com.nass.infrastructure.entities.common;

import com.nass.infrastructure.entities.base.BaseEntity;
import com.nass.infrastructure.entities.grammar.GrammarExampleEntity;
import com.nass.infrastructure.entities.grammar.GrammarMeaningEntity;
import com.nass.infrastructure.entities.grammar.GrammarNoteEntity;
import com.nass.infrastructure.entities.grammar.GrammarStructureEntity;
import com.nass.infrastructure.entities.vocabulary.VocabularyMeaningEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "sentence")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SentenceEntity extends BaseEntity<Long> {
    String japanese;
    String vietnamese;
    String english;

    @ManyToOne
    @JoinColumn(name = "grammar_meaning_id")
    GrammarMeaningEntity grammarMeaning;

    @ManyToOne
    @JoinColumn(name = "grammar_structure_id")
    GrammarStructureEntity grammarStructure;

    @ManyToOne
    @JoinColumn(name = "grammar_example_id")
    GrammarExampleEntity grammarExample;

    @ManyToOne
    @JoinColumn(name = "grammar_note_id")
    GrammarNoteEntity grammarNote;

    @ManyToOne
    @JoinColumn(name = "vocabulary_meaning_id")
    VocabularyMeaningEntity vocabularyMeaning;
}
