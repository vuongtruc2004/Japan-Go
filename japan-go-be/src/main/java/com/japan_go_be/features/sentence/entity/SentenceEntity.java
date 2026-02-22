package com.japan_go_be.features.sentence.entity;

import com.japan_go_be.common.persistence.BaseEntity;
import com.japan_go_be.features.grammar.entity.GrammarExampleEntity;
import com.japan_go_be.features.grammar.entity.GrammarMeaningEntity;
import com.japan_go_be.features.grammar.entity.GrammarNoteEntity;
import com.japan_go_be.features.grammar.entity.GrammarStructureEntity;
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
public class SentenceEntity extends BaseEntity {
    @Lob
    @Column(name = "japanese_raw", columnDefinition = "MEDIUMTEXT")
    String japaneseRaw;

    @Lob
    @Column(name = "vietnamese_raw", columnDefinition = "MEDIUMTEXT")
    String vietnameseRaw;

    @Lob
    @Column(name = "english_raw", columnDefinition = "MEDIUMTEXT")
    String englishRaw;

    @Lob
    @Column(name = "japaneses_html", columnDefinition = "MEDIUMTEXT")
    String japaneseHtml;

    @Lob
    @Column(name = "vietnamese_html", columnDefinition = "MEDIUMTEXT")
    String vietnameseHtml;

    @Lob
    @Column(name = "english_html", columnDefinition = "MEDIUMTEXT")
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
