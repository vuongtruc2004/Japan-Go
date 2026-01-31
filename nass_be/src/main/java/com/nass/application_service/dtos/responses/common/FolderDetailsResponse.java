package com.nass.application_service.dtos.responses.common;

import com.nass.application_service.dtos.responses.lesson.LessonResponse;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class FolderDetailsResponse extends FolderResponse {
    List<LessonResponse> lessons;
}
