package org.japan.dto.response.lesson;

import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;
import org.japan.dto.response.base.BaseResponse;
import org.japan.dto.response.grammar.GrammarResponse;

import java.util.ArrayList;
import java.util.List;

@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class GrammarLessonResponse extends BaseResponse {
    @Builder.Default
    List<GrammarResponse> grammars = new ArrayList<>();
}
