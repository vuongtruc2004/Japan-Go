package com.nass.business.kanji.entity;

import com.nass.common.persistence.BaseEntity;
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
@Table(name = "sino_vietnamese")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SinoVietnameseEntity extends BaseEntity<Long> {
    @Column(name = "reading_text", nullable = false)
    String readingText;

    @Builder.Default
    @OneToMany(mappedBy = "sinoVietnamese", cascade = CascadeType.ALL, orphanRemoval = true)
    List<SinoVietnameseMeaningEntity> sinoVietnameseMeaningList = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "mainSinoVietnamese")
    List<KanjiEntity> mainKanjiList = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "kanji_id")
    KanjiEntity kanji;
}
