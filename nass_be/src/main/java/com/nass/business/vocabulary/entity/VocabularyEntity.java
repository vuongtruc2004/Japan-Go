package com.nass.business.vocabulary.entity;

import com.nass.business.kanji.entity.KanjiPageEntity;
import com.nass.common.persistence.BaseEntity;
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
public class VocabularyEntity extends BaseEntity<Long> {
    @Column(nullable = false)
    String japanese;

    @Column(nullable = false)
    String reading;

    String vietnamese;

    String english;

    String note;

    @Builder.Default
    @ManyToMany(mappedBy = "vocabularies")
    List<KanjiPageEntity> kanjiPages = new ArrayList<>();
}
