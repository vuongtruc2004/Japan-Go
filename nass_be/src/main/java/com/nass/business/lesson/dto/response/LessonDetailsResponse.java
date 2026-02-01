package com.nass.business.lesson.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class LessonDetailsResponse extends LessonResponse {
    GrammarLessonResponse grammarLesson;

    KanjiLessonResponse kanjiLesson;
}
