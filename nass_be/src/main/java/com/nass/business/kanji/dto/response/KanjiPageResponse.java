package com.nass.business.kanji.dto.response;

import com.nass.business.vocabulary.dto.response.VocabularyResponse;
import com.nass.common.dto.BaseResponse;
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
public class KanjiPageResponse extends BaseResponse<Integer> {
    KanjiResponse mainKanji;

    @Builder.Default
    List<VocabularyResponse> vocabularies = new ArrayList<>();
}
