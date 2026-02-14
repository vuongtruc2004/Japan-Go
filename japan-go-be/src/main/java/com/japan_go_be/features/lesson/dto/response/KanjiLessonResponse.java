package com.japan_go_be.features.lesson.dto.response;

import com.japan_go_be.common.dto.BaseResponse;
import com.japan_go_be.features.kanji.dto.response.KanjiPageResponse;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class KanjiLessonResponse extends BaseResponse {
    List<KanjiPageResponse> kanjiPages;
}
