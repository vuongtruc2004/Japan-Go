package com.japan_go_be.features.lesson.dto.response;

import com.japan_go_be.common.dto.BaseResponse;
import com.japan_go_be.features.kanji.dto.response.KanjiPageResponse;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

import java.util.List;

@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class KanjiLessonResponse extends BaseResponse {
    List<KanjiPageResponse> kanjiPages;
}
