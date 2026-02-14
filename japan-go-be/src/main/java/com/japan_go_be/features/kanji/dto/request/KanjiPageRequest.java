package com.japan_go_be.features.kanji.dto.request;

import com.japan_go_be.features.vocabulary.dto.request.VocabularyRequest;

import java.util.List;

public record KanjiPageRequest(
        String mainKanjiCharacter,
        List<VocabularyRequest> vocabularies
) {
}
