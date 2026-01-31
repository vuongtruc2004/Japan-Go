package com.nass.application_service.dtos.responses.lesson;

import com.nass.application_service.dtos.responses.grammar.GrammarResponse;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class GrammarLessonDetailsResponse extends GrammarLessonResponse {
    List<GrammarResponse> grammars = new ArrayList<>();
}
