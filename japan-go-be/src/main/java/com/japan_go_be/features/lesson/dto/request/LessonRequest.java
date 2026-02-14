package com.japan_go_be.features.lesson.dto.request;

import com.japan_go_be.features.lesson.constant.LessonTypeEnum;

public record LessonRequest(
        Long id,
        String lessonName,
        String description,
        LessonTypeEnum lessonType,
        GrammarLessonRequest grammarLesson,
        KanjiLessonRequest kanjiLesson
) {
}
