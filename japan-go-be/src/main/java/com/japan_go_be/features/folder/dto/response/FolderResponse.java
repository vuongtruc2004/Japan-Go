package com.japan_go_be.features.folder.dto.response;

import com.japan_go_be.common.dto.BaseResponse;
import com.japan_go_be.features.lesson.dto.response.LessonResponse;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

import java.util.List;

@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class FolderResponse extends BaseResponse {
    String folderName;

    Long parentId;

    Long lessonCount;

    List<LessonResponse> lessons;
}
