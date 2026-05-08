package org.japan.dto.request.kanji;

public record VocabularyRequest(
        String japanese,
        String reading,
        String meaning,
        String note
) {
}
