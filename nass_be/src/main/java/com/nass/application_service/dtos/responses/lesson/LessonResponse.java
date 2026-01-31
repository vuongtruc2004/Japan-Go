package com.nass.application_service.dtos.responses.lesson;

import com.nass.application_service.dtos.responses.base.BaseResponse;
import com.nass.contract.enums.lesson.LessonTypeEnum;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class LessonResponse extends BaseResponse<Integer> {
    String lessonName;

    LessonTypeEnum lessonType;
}
