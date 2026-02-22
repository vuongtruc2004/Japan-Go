package com.japan_go_be.features.lesson.dto.request;

import com.japan_go_be.features.kanji.dto.request.KanjiPageRequest;
import com.japan_go_be.features.lesson.constant.LessonTypeEnum;

import java.util.List;

public record KanjiLessonRequest(
        Long lessonId,
        Long folderId,
        String lessonName,
        String description,
        LessonTypeEnum lessonType,
        List<KanjiPageRequest> kanjiPages
) {

}
