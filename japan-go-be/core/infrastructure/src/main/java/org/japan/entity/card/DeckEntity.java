package org.japan.entity.card;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.japan.entity.base.BaseEntity;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "decks")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DeckEntity extends BaseEntity {
    @Column(nullable = false)
    String title;

    String description;

    @Builder.Default
    @OneToMany(mappedBy = "deck", cascade = CascadeType.ALL, orphanRemoval = true)
    List<CardEntity> cards = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "deck")
    List<StudySessionEntity> studySessions = new ArrayList<>();
}
