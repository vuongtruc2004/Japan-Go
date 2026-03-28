package com.japan_go_be.business.dto.requests.lesson;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public record GrammarLessonRequest(
        Long folderId,
        Integer jlptLevel,
        Long bookId,
        List<MultipartFile> files
) {
}
