package com.japan_go_be.business.dto.responses.kanji;

import com.japan_go_be.business.dto.responses.base.BaseResponse;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;

@SuperBuilder
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
