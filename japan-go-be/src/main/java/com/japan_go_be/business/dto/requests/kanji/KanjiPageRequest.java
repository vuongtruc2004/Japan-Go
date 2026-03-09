package com.japan_go_be.business.dto.requests.kanji;

import java.util.List;

public record KanjiPageRequest(
        String mainKanjiCharacter,
        List<VocabularyRequest> vocabularies
) {
}
