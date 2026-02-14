package com.japan_go_be.features.sentence.dto.response;

import com.japan_go_be.common.dto.BaseResponse;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SentenceResponse extends BaseResponse {
    String japaneseRaw;
    String vietnameseRaw;
    String englishRaw;

    String japaneseHtml;
    String vietnameseHtml;
    String englishHtml;
}
