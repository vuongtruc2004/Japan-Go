package com.nass.business.lesson.dto.response;

import com.nass.business.grammar.dto.response.GrammarResponse;
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
