package com.japan_go_be.infrastructure.entities.grammar;

import com.japan_go_be.infrastructure.entities.base.BaseEntity;
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
@Table(name = "grammar_examples")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class GrammarExampleEntity extends BaseEntity {
    @Builder.Default
    @OneToMany(mappedBy = "grammarExample", cascade = CascadeType.ALL, orphanRemoval = true)
    List<SentenceEntity> sentences = new ArrayList<>();

    @OneToOne(mappedBy = "grammarExample")
    GrammarEntity grammar;
}
