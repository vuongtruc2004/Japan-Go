package com.japan_go_be.business.dto.responses.sentence;

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
public class SentenceResponse extends BaseResponse {
    String japaneseRaw;
    String vietnameseRaw;
    String englishRaw;

    String japaneseHtml;
    String vietnameseHtml;
    String englishHtml;
}
