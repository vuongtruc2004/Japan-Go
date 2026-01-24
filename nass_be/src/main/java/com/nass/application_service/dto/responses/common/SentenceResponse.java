package com.nass.application_service.dto.responses.common;

import com.nass.application_service.dto.responses.base.BaseResponse;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SentenceResponse extends BaseResponse<Long> {
    String japanese;
    String vietnamese;
    String english;
}
