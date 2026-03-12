package com.japan_go_be.infrastructure.entities.card;

import com.japan_go_be.infrastructure.entities.base.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
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
@Table(name = "deck")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DeckEntity extends BaseEntity {
    String title;

    String description;

    @Builder.Default
    @OneToMany(mappedBy = "deck")
    List<CardEntity> cards = new ArrayList<>();
}
