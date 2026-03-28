package com.japan_go_be.infrastructure.entities.kanji;

import com.japan_go_be.infrastructure.entities.base.BaseEntity;
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
@Table(name = "kanji")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class KanjiEntity extends BaseEntity {
    @Column(name = "kanji_character", unique = true, nullable = false)
    String kanjiCharacter;

    @Column(unique = true, nullable = false)
    String unicode;

    /*
     * LONGTEXT: là kiểu dữ liệu ở tầng database (MySQL) để lưu text rất lớn.
     * @Lob:     là annotation ở tầng JPA/Hibernate, báo cho ORM (Object Relational Mapping) rằng
     *           đây là "Large Object" (CLOB) để xử lý đúng cách.
     *
     * => Cần @Lob để Hibernate map chính xác và tránh bị giới hạn
     *    mặc định như VARCHAR(255).
     */
    @Lob
    @Column(name = "kanji_vg", columnDefinition = "LONGTEXT")
    String kanjiVg;

    Integer grade;

    @Column(name = "stroke_count")
    Integer strokeCount;

    Integer frequency;

    @Column(name = "jlpt_level")
    Integer jlptLevel;

    @ManyToOne
    @JoinColumn(name = "main_sino_vietnamese_id")
    SinoVietnameseEntity mainSinoVietnamese;

    @Builder.Default
    @OneToMany(mappedBy = "kanji", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    List<SinoVietnameseEntity> sinoVietnameseList = new ArrayList<>();

    @Builder.Default
    @ManyToMany
    @JoinTable(name = "kanji_onyomi",
            joinColumns = @JoinColumn(name = "kanji_id"),
            inverseJoinColumns = @JoinColumn(name = "onyomi_id"))
    List<OnyomiEntity> onyomiList = new ArrayList<>();

    @Builder.Default
    @ManyToMany
    @JoinTable(name = "kanji_kunyomi",
            joinColumns = @JoinColumn(name = "kanji_id"),
            inverseJoinColumns = @JoinColumn(name = "kunyomi_id"))
    List<KunyomiEntity> kunyomiList = new ArrayList<>();

    @Builder.Default
    @ManyToMany
    @JoinTable(name = "kanji_kanji_meaning",
            joinColumns = @JoinColumn(name = "kanji_id"),
            inverseJoinColumns = @JoinColumn(name = "kanji_meaning_id"))
    List<KanjiMeaningEntity> kanjiMeaningList = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "mainKanji")
    List<KanjiPageEntity> kanjiPages = new ArrayList<>();
}
