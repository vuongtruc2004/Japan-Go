package com.japan_go_be.business.dto.requests.lesson;

import java.util.List;

public record ExportGrammarLessonRequest(List<Long> grammarLessonIds) {
}
