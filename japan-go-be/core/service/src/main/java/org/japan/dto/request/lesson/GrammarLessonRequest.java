package org.japan.dto.request.lesson;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public record GrammarLessonRequest(
        Long folderId,
        Integer jlptLevel,
        Long bookId,
        List<MultipartFile> files
) {
}
