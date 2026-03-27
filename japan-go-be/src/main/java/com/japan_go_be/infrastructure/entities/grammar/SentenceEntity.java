package com.japan_go_be.infrastructure.entities.grammar;

import com.japan_go_be.infrastructure.entities.base.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "sentences")
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
    @Column(name = "japanese_html", columnDefinition = "MEDIUMTEXT")
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
