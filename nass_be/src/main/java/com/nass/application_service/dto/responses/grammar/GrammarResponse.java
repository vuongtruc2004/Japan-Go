package com.nass.application_service.dto.responses.grammar;

import com.nass.application_service.dto.responses.base.BaseResponse;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class GrammarResponse extends BaseResponse<Integer> {
    String grammarTitle;

    GrammarMeaningResponse grammarMeaning;

    GrammarStructureResponse grammarStructure;

    GrammarExampleResponse grammarExample;

    GrammarNoteResponse grammarNote;
}
