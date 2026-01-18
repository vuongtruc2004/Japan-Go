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
@Table(name = "grammar_meaning")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class GrammarMeaningEntity extends BaseEntity<Integer> {
    @Column(name = "vietnamese_translation")
    String vietnameseTranslation;

    @Builder.Default
    @OneToMany(mappedBy = "grammarMeaning", cascade = CascadeType.ALL, orphanRemoval = true)
    List<SentenceEntity> sentences = new ArrayList<>();

    @OneToOne(mappedBy = "grammarMeaning")
    GrammarEntity grammar;
}
