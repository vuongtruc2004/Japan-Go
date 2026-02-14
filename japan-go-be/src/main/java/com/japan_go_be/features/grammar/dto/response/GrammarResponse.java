package com.japan_go_be.features.grammar.dto.response;

import com.japan_go_be.common.dto.BaseResponse;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class GrammarResponse extends BaseResponse {
    String grammarTitle;

    GrammarMeaningResponse grammarMeaning;

    GrammarStructureResponse grammarStructure;

    GrammarExampleResponse grammarExample;

    GrammarNoteResponse grammarNote;
}
