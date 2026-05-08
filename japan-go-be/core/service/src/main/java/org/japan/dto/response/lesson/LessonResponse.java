package org.japan.dto.response.lesson;

import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;
import org.japan.constants.lesson.LessonTypeEnum;
import org.japan.dto.response.base.BaseResponse;

@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class LessonResponse extends BaseResponse {
    String lessonName;
    String description;
    LessonTypeEnum lessonType;
    Long pageCount;
    GrammarLessonResponse grammarLesson;
    KanjiLessonResponse kanjiLesson;
    BookResponse book;
}
