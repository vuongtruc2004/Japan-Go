package com.japan_go_be.features.vocabulary.dto.response;

import com.japan_go_be.common.dto.BaseResponse;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Builder
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
}
