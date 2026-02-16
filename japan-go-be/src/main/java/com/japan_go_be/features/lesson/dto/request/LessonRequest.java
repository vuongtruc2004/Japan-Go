package com.japan_go_be.features.lesson.dto.request;

import com.japan_go_be.features.lesson.constant.LessonTypeEnum;

public record LessonRequest(
        Long lessonId,
        Long folderId,
        String lessonName,
        String description,
        LessonTypeEnum lessonType,
        GrammarLessonRequest grammarLesson,
        KanjiLessonRequest kanjiLesson
) {
}
