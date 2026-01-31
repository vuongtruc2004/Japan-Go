package com.nass.infrastructure.entities.common;

import com.nass.infrastructure.entities.base.BaseEntity;
import com.nass.infrastructure.entities.grammar.GrammarExampleEntity;
import com.nass.infrastructure.entities.grammar.GrammarMeaningEntity;
import com.nass.infrastructure.entities.grammar.GrammarNoteEntity;
import com.nass.infrastructure.entities.grammar.GrammarStructureEntity;
import jakarta.persistence.*;
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
    @Column(name = "japanese_raw")
    String japaneseRaw;
    @Column(name = "vietnamese_raw")
    String vietnameseRaw;
    @Column(name = "english_raw")
    String englishRaw;

    @Column(name = "japaneses_html")
    String japaneseHtml;
    @Column(name = "vietnamese_html")
    String vietnameseHtml;
    @Column(name = "english_html")
    String englishHtml;


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
}
