package com.japan_go_be.features.kanji.dto.response;

import com.japan_go_be.common.dto.BaseResponse;
import com.japan_go_be.features.vocabulary.dto.response.VocabularyResponse;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class KanjiPageResponse extends BaseResponse {
    KanjiResponse mainKanji;

    @Builder.Default
    List<VocabularyResponse> vocabularies = new ArrayList<>();
}
