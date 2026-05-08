package org.japan.dto.request.kanji;

public record CreateSinoVietnameseRequest(
        Long kanjiId,
        String readingText
) {
}
