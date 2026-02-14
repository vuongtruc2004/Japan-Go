package com.japan_go_be.features.vocabulary.dto.request;

public record VocabularyRequest(
        String japanese,
        String reading,
        String vietnamese,
        String note,
        String english
) {
}
