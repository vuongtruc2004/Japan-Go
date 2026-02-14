package com.japan_go_be.features.lesson.dto.request;

import com.japan_go_be.features.kanji.dto.request.KanjiPageRequest;

import java.util.List;

public record KanjiLessonRequest(
        List<KanjiPageRequest> kanjiPages
) {

}
