package com.nass.business.vocabulary.dto.response;

import com.nass.common.dto.BaseResponse;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class VocabularyResponse extends BaseResponse<Long> {
    String japanese;
    String reading;
    String vietnamese;
    String english;
    String note;
}
