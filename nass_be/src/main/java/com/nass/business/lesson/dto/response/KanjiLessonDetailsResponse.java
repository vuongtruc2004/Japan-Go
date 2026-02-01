package com.nass.business.lesson.dto.response;

import com.nass.business.kanji.dto.response.KanjiPageResponse;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class KanjiLessonDetailsResponse extends KanjiLessonResponse {
    List<KanjiPageResponse> kanjiPages = new ArrayList<>();
}
