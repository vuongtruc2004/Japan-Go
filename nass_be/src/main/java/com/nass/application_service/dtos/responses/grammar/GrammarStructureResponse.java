package com.nass.application_service.dtos.responses.grammar;

import com.nass.application_service.dtos.responses.base.BaseResponse;
import com.nass.application_service.dtos.responses.common.SentenceResponse;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class GrammarStructureResponse extends BaseResponse<Integer> {
    List<SentenceResponse> sentences;
}
