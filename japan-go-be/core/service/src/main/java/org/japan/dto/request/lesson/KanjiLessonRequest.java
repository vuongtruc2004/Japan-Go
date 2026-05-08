package org.japan.dto.request.lesson;

import org.japan.constants.lesson.LessonTypeEnum;
import org.japan.dto.request.kanji.KanjiPageRequest;

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
