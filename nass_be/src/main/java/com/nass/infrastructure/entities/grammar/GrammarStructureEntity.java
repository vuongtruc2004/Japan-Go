package com.nass.infrastructure.entities.grammar;

import com.nass.infrastructure.entities.base.BaseEntity;
import com.nass.infrastructure.entities.common.SentenceEntity;
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
@Table(name = "grammar_structure")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class GrammarStructureEntity extends BaseEntity<Integer> {
    @Builder.Default
    @OneToMany(mappedBy = "grammarStructure", cascade = CascadeType.ALL, orphanRemoval = true)
    List<SentenceEntity> sentences = new ArrayList<>();

    @OneToOne(mappedBy = "grammarStructure")
    GrammarEntity grammar;
}
