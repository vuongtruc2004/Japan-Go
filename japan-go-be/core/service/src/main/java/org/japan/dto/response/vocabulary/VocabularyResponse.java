package org.japan.dto.response.vocabulary;

import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;
import org.japan.dto.response.base.BaseResponse;

import java.util.ArrayList;
import java.util.List;

@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class VocabularyResponse extends BaseResponse {
    String japanese;
    String reading;
    String meaning;
    String note;

    @Builder.Default
    List<String> kanjiVgList = new ArrayList<>();

    @Builder.Default
    List<SupportVocabularyResponse> supportVocabularies = new ArrayList<>();
}
