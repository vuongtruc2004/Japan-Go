package com.japan_go_be.features.vocabulary.dto.request;

public record VocabularyRequest(
        String japanese,
        String reading,
        String meaning,
        String note
) {
}
