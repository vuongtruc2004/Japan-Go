package com.japan_go_be.business.dto.responses.lesson;

import com.japan_go_be.business.dto.responses.base.BaseResponse;
import com.japan_go_be.contract.constants.lesson.LessonTypeEnum;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

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
}
