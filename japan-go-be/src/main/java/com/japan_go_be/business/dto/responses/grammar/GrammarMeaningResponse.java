package com.japan_go_be.business.dto.responses.grammar;

import com.japan_go_be.business.dto.responses.base.BaseResponse;
import com.japan_go_be.business.dto.responses.sentence.SentenceResponse;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

import java.util.List;

@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class GrammarMeaningResponse extends BaseResponse {
    List<SentenceResponse> sentences;
}
