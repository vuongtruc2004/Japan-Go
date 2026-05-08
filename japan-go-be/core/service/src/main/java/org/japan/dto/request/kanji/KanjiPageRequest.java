package org.japan.dto.request.kanji;

import java.util.List;

public record KanjiPageRequest(
        String mainKanjiCharacter,
        List<VocabularyRequest> vocabularies
) {
}
