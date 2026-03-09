package com.japan_go_be.business.dto.responses.kanji;

import com.japan_go_be.business.dto.responses.base.BaseResponse;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class VocabularyResponse extends BaseResponse {
    String japanese;
    String sinoVietnamese;
    String reading;
    String meaning;
    String note;
}
