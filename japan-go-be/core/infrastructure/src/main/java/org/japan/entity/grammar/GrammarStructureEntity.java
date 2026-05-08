package org.japan.entity.grammar;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.japan.entity.base.BaseEntity;

import java.util.ArrayList;
import java.util.List;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "grammar_structures")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class GrammarStructureEntity extends BaseEntity {
    @Builder.Default
    @OneToMany(mappedBy = "grammarStructure", cascade = CascadeType.ALL, orphanRemoval = true)
    List<SentenceEntity> sentences = new ArrayList<>();

    @OneToOne(mappedBy = "grammarStructure")
    GrammarEntity grammar;
}
