package com.japan_go_be.features.grammar.dto.response;

import com.japan_go_be.common.dto.BaseResponse;
import com.japan_go_be.features.sentence.dto.response.SentenceResponse;
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
public class GrammarNoteResponse extends BaseResponse {
    List<SentenceResponse> sentences;
}
