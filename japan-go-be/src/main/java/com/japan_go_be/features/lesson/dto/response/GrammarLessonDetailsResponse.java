package com.japan_go_be.features.lesson.dto.response;

import com.japan_go_be.features.grammar.dto.response.GrammarResponse;
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
