package org.japan.dto.response.grammar;

import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;
import org.japan.dto.response.base.BaseResponse;
import org.japan.dto.response.sentence.SentenceResponse;

import java.util.List;

@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class GrammarStructureResponse extends BaseResponse {
    List<SentenceResponse> sentences;
}
