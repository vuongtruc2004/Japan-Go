package com.japan_go_be.features.vocabulary.entity;

import com.japan_go_be.common.persistence.BaseEntity;
import com.japan_go_be.features.kanji.entity.KanjiPageEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
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
@Table(name = "vocabulary")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class VocabularyEntity extends BaseEntity {
    @Column(nullable = false)
    String japanese;

    @Column(nullable = false)
    String reading;

    String meaning;

    String note;
    
    @Builder.Default
    @ManyToMany(mappedBy = "vocabularies")
    List<KanjiPageEntity> kanjiPages = new ArrayList<>();
}
