package com.nass.application_service.dto.responses.lesson;

import com.nass.contract.enums.LessonTypeEnum;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class LessonResponse {
    // like a file name
    String lessonName;

    LessonTypeEnum lessonType;

    GrammarLessonResponse grammarLesson;

    KanjiLessonResponse kanjiLesson;
}
