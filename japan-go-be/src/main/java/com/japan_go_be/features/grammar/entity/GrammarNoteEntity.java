package com.japan_go_be.features.grammar.entity;

import com.japan_go_be.common.persistence.BaseEntity;
import com.japan_go_be.features.sentence.entity.SentenceEntity;
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
@Table(name = "grammar_note")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class GrammarNoteEntity extends BaseEntity {
    @Builder.Default
    @OneToMany(mappedBy = "grammarNote", cascade = CascadeType.ALL, orphanRemoval = true)
    List<SentenceEntity> sentences = new ArrayList<>();

    @OneToOne(mappedBy = "grammarNote")
    GrammarEntity grammar;
}
