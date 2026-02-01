package com.nass.business.kanji.entity;

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
@Table(name = "kunyomi")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class KunyomiEntity extends BaseEntity<Long> {
    @Column(name = "reading_text", unique = true, nullable = false)
    String readingText;

    @Builder.Default
    @ManyToMany(mappedBy = "kunyomiList")
    List<KanjiEntity> kanjiList = new ArrayList<>();
}
