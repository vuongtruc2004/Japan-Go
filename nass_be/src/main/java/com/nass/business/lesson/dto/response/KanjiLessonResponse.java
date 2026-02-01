package com.nass.business.lesson.dto.response;

import com.nass.business.kanji.dto.response.KanjiPageResponse;
import com.nass.common.dto.BaseResponse;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class KanjiLessonResponse extends BaseResponse<Integer> {
    List<KanjiPageResponse> kanjiPages;
}
