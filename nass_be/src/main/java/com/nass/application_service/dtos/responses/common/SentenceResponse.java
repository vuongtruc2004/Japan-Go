package com.nass.application_service.dtos.responses.common;

import com.nass.application_service.dtos.responses.base.BaseResponse;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SentenceResponse extends BaseResponse<Long> {
    String japaneseRaw;
    String vietnameseRaw;
    String englishRaw;

    String japaneseHtml;
    String vietnameseHtml;
    String englishHtml;
}
