package com.japan_go_be.infrastructure.entities.kanji;

import com.japan_go_be.infrastructure.entities.base.BaseEntity;
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
@Table(name = "onyomi")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OnyomiEntity extends BaseEntity {
    @Column(name = "reading_text", unique = true, nullable = false)
    String readingText;

    @Builder.Default
    @ManyToMany(mappedBy = "onyomiList")
    List<KanjiEntity> kanjiList = new ArrayList<>();
}
