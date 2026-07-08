package org.japan.dto.request.lesson;

import org.japan.constants.lesson.LessonTypeEnum;
import org.springframework.web.multipart.MultipartFile;

public record KanjiLessonImportRequest(
        Long folderId,
        Long bookId,
        String lessonName,
        String description,
        LessonTypeEnum lessonType,
        MultipartFile file
) {

}
