package com.japan_go_be.infrastructure.entities.card;

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
