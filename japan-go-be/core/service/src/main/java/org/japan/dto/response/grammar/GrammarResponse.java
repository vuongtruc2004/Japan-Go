package org.japan.dto.response.grammar;

import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;
import org.japan.dto.response.base.BaseResponse;

@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class GrammarResponse extends BaseResponse {
    String grammarTitle;
    String grammarTitleFurigana;
    String grammarTitleRomaji;
    String translation;
    Integer jlptLevel;

    String bookTitle;
    String lessonName;
    Long lessonId;

    GrammarMeaningResponse grammarMeaning;

    GrammarStructureResponse grammarStructure;

    GrammarExampleResponse grammarExample;

    GrammarNoteResponse grammarNote;
}
