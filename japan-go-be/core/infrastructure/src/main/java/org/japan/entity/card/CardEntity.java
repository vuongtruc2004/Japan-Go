package org.japan.entity.card;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.japan.entity.base.BaseEntity;
import org.japan.entity.grammar.GrammarEntity;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "cards")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CardEntity extends BaseEntity {
    @Lob
    @Column(nullable = false, columnDefinition = "MEDIUMTEXT")
    String front;

    // use for back content of the flashcard
    @ManyToOne
    @JoinColumn(name = "grammar_id")
    GrammarEntity grammar;

    @Column(nullable = false)
    Integer position;

    @ManyToOne
    @JoinColumn(name = "deck_id")
    DeckEntity deck;

    @Builder.Default
    @OneToMany(mappedBy = "card")
    List<StudySessionCardEntity> studySessionCards = new ArrayList<>();
}
