package com.japan_go_be.infrastructure.entities.card;

import com.japan_go_be.infrastructure.entities.base.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "flash_card")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CardEntity extends BaseEntity {
    @Lob
    @Column(nullable = false, columnDefinition = "MEDIUMTEXT")
    String front;

    @Lob
    @Column(nullable = false, columnDefinition = "MEDIUMTEXT")
    String back;

    @Column(nullable = false)
    Integer position;

    @ManyToOne
    @JoinColumn(name = "deck_id")
    DeckEntity deck;
}
