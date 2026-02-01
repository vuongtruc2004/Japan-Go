package com.nass.business.grammar.dto.response;

import com.nass.business.sentence.dto.response.SentenceResponse;
import com.nass.common.dto.BaseResponse;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class GrammarNoteResponse extends BaseResponse<Integer> {
    List<SentenceResponse> sentences;
}
