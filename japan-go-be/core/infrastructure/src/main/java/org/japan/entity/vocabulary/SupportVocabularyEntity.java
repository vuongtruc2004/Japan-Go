package org.japan.entity.vocabulary;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.japan.entity.base.BaseEntity;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "support_vocabularies")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SupportVocabularyEntity extends BaseEntity {
    @Column(nullable = false)
    String japanese;

    @Column(nullable = false)
    String reading;

    String meaning;

    @ManyToOne
    @JoinColumn(name = "vocabulary_id")
    VocabularyEntity vocabulary;
}
