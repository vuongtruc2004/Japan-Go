package com.nass.infrastructure.entities.grammar;

import com.nass.infrastructure.entities.base.BaseEntity;
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
@Table(name = "additional_note")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AdditionalNote extends BaseEntity<Integer> {
    @Builder.Default
    @OneToMany(mappedBy = "additionalNote", cascade = CascadeType.ALL, orphanRemoval = true)
    List<SentenceEntity> sentences = new ArrayList<>();

    @OneToOne(mappedBy = "additionalNote")
    GrammarEntity grammar;
}
