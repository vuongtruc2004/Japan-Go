package com.nass.infrastructure.entities.kanji;

import com.nass.infrastructure.entities.base.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "kanji")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class KanjiEntity extends BaseEntity<Integer> {
    @Column(name = "kanji_character")
    Character kanjiCharacter;

    @Column(unique = true, nullable = false)
    String unicode;

    Integer grade;

    @Column(name = "stroke_count")
    Integer strokeCount;

    Integer frequency;

    @Column(name = "jlpt_level")
    Integer jlptLevel;

    @OneToMany(mappedBy = "kanji", cascade = CascadeType.ALL, orphanRemoval = true)
    List<SinoVietnameseEntity> sinoVietnamese = new ArrayList<>();

    @OneToMany(mappedBy = "kanji", cascade = CascadeType.ALL, orphanRemoval = true)
    List<OnyomiEntity> onyomi = new ArrayList<>();

    @OneToMany(mappedBy = "kanji", cascade = CascadeType.ALL, orphanRemoval = true)
    List<KunyomiEntity> kunyomi = new ArrayList<>();

    @OneToMany(mappedBy = "kanji", cascade = CascadeType.ALL, orphanRemoval = true)
    List<MeaningEntity> meanings = new ArrayList<>();
}
