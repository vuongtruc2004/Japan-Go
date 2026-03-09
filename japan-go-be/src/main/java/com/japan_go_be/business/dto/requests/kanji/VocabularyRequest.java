package com.japan_go_be.business.dto.requests.kanji;

public record VocabularyRequest(
        String japanese,
        String reading,
        String meaning,
        String note
) {
}
