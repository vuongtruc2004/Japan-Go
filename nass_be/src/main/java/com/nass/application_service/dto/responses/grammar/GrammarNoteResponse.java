package com.nass.application_service.dto.responses.grammar;

import com.nass.application_service.dto.responses.base.BaseResponse;
import com.nass.application_service.dto.responses.common.SentenceResponse;
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
