package com.nass.infrastructure.entities.vocabulary;

import com.nass.contract.enums.VocabularyTypeEnum;
import com.nass.infrastructure.entities.base.BaseEntity;
import jakarta.persistence.*;
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
@Table(name = "vocabulary_type")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class VocabularyTypeEntity extends BaseEntity<Integer> {
    @Column(name = "vocabulary_type", nullable = false, unique = true)
    @Enumerated(EnumType.STRING)
    VocabularyTypeEnum vocabularyType;

    @Builder.Default
    @ManyToMany(mappedBy = "vocabularyTypes")
    List<VocabularyEntity> vocabularies = new ArrayList<>();
}
