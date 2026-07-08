package org.japan.dto.response.vocabulary;

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
public class SupportVocabularyResponse extends BaseResponse {
    String japanese;
    String reading;
    String meaning;
}
