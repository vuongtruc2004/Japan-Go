package com.nass.application_service.dtos.responses.vocabulary;

import com.nass.application_service.dtos.responses.base.BaseResponse;
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
