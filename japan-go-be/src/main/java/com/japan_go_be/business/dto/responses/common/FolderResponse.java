package com.japan_go_be.business.dto.responses.common;

import com.japan_go_be.business.dto.responses.base.BaseResponse;
import com.japan_go_be.business.dto.responses.lesson.LessonResponse;
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

    Boolean isPinnedToSidebar;

    Long parentId;

    Long lessonCount;

    List<LessonResponse> lessons;
}
