package com.japan_go_be.features.lesson.entity;

import com.japan_go_be.common.persistence.BaseEntity;
import com.japan_go_be.features.kanji.entity.KanjiPageEntity;
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
public class KanjiLessonEntity extends BaseEntity {
    @Builder.Default
    @OneToMany(mappedBy = "kanjiLesson", cascade = CascadeType.ALL, orphanRemoval = true)
    List<KanjiPageEntity> kanjiPages = new ArrayList<>();

    @OneToOne(mappedBy = "kanjiLesson")
    LessonEntity lesson;
}
