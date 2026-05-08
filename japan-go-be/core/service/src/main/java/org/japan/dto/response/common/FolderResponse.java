package org.japan.dto.response.common;

import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;
import org.japan.dto.response.base.BaseResponse;
import org.japan.dto.response.lesson.LessonResponse;

import java.util.List;

@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class FolderResponse extends BaseResponse {
    String folderName;

    Boolean isPinnedToSidebar;

    Long parentId;

    Long lessonCount;

    List<LessonResponse> lessons;
}
