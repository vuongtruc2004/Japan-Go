package com.nass.business.lesson.entity;

import com.nass.business.kanji.entity.KanjiPageEntity;
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
@Table(name = "kanji_lesson")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class KanjiLessonEntity extends BaseEntity<Integer> {
    @Builder.Default
    @ManyToMany
    @JoinTable(name = "kanji_lesson_kanji_page",
            joinColumns = @JoinColumn(name = "kanji_lesson_id"),
            inverseJoinColumns = @JoinColumn(name = "kanji_page_id"))
    List<KanjiPageEntity> kanjiPages = new ArrayList<>();

    @OneToOne(mappedBy = "kanjiLesson")
    LessonEntity lesson;
}
