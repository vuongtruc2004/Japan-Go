package com.nass.application_service.dtos.responses.lesson;

import com.nass.application_service.dtos.responses.grammar.GrammarResponse;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class GrammarLessonResponse {
    String grammarLessonTitle;

    List<GrammarResponse> grammars = new ArrayList<>();
}
