package com.nass.business.grammar.entity;

import com.nass.business.sentence.entity.SentenceEntity;
import com.nass.common.persistence.BaseEntity;
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
@Table(name = "grammar_example")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class GrammarExampleEntity extends BaseEntity<Integer> {
    @Builder.Default
    @OneToMany(mappedBy = "grammarExample", cascade = CascadeType.ALL, orphanRemoval = true)
    List<SentenceEntity> sentences = new ArrayList<>();

    @OneToOne(mappedBy = "grammarExample")
    GrammarEntity grammar;
}
