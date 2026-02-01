package com.nass.business.lesson.dto.response;

import com.nass.common.dto.BaseResponse;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class GrammarLessonResponse extends BaseResponse<Integer> {
    String grammarLessonTitle;
}
