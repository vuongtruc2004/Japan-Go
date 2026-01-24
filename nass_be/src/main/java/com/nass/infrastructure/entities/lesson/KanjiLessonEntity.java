package com.nass.infrastructure.entities.lesson;

import com.nass.infrastructure.entities.base.BaseEntity;
import com.nass.infrastructure.entities.kanji.KanjiPageEntity;
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
@Table(name = "kanji_lesson")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class KanjiLessonEntity extends BaseEntity<Integer> {
    @Column(name = "kanji_lesson_title", nullable = false)
    String kanjiLessonTitle;

    @Builder.Default
    @ManyToMany
    @JoinTable(name = "kanji_lesson_kanji_page",
            joinColumns = @JoinColumn(name = "kanji_lesson_id"),
            inverseJoinColumns = @JoinColumn(name = "kanji_page_id"))
    List<KanjiPageEntity> kanjiPages = new ArrayList<>();

    @OneToOne(mappedBy = "kanjiLesson")
    LessonEntity lesson;
}
