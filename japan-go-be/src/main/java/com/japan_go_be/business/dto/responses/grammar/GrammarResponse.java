package com.japan_go_be.business.dto.responses.grammar;

import com.japan_go_be.business.dto.responses.base.BaseResponse;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

@SuperBuilder
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
