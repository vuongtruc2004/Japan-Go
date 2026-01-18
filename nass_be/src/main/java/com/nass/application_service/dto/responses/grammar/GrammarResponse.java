package com.nass.application_service.dto.responses.grammar;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class GrammarResponse {
    String grammarTitle;

    GrammarMeaningResponse grammarMeaning;

    StructureResponse structure;

    ExampleResponse example;

    AdditionalNoteResponse additionalNote;
}
