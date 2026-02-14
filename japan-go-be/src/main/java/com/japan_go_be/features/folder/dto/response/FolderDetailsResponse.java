package com.japan_go_be.features.folder.dto.response;

import com.japan_go_be.features.lesson.dto.response.LessonResponse;
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
