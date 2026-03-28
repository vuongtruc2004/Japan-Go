package com.japan_go_be.business.dto.requests.lesson;

import com.japan_go_be.business.dto.requests.kanji.KanjiPageRequest;
import com.japan_go_be.contract.constants.lesson.LessonTypeEnum;

import java.util.List;

public record KanjiLessonRequest(
        Long lessonId,
        Long folderId,
        Long bookId,
        String lessonName,
        String description,
        LessonTypeEnum lessonType,
        List<KanjiPageRequest> kanjiPages
) {

}
