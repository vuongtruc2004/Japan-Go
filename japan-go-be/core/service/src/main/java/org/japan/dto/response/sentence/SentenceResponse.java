package org.japan.dto.response.sentence;

import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;
import org.japan.dto.response.base.BaseResponse;

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
