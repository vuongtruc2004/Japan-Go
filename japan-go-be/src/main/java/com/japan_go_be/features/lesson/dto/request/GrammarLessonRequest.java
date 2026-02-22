package com.japan_go_be.features.lesson.dto.request;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public record GrammarLessonRequest(List<MultipartFile> files, Long folderId) {
}
