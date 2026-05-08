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
@Table(name = "grammar_notes")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class GrammarNoteEntity extends BaseEntity {
    @Builder.Default
    @OneToMany(mappedBy = "grammarNote", cascade = CascadeType.ALL, orphanRemoval = true)
    List<SentenceEntity> sentences = new ArrayList<>();

    @OneToOne(mappedBy = "grammarNote")
    GrammarEntity grammar;
}
