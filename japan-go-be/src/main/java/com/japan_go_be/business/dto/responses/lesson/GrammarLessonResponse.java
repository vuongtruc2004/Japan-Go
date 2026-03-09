package com.japan_go_be.business.dto.responses.lesson;

import com.japan_go_be.business.dto.responses.base.BaseResponse;
import com.japan_go_be.business.dto.responses.grammar.GrammarResponse;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;

@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class GrammarLessonResponse extends BaseResponse {
    List<GrammarResponse> grammars = new ArrayList<>();
}
