package com.nass.application_service.dto.responses.kanji;

import com.nass.application_service.dto.responses.base.BaseResponse;
import com.nass.application_service.dto.responses.vocabulary.VocabularyResponse;
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
