package com.nass.business.folder.dto.response;

import com.nass.business.lesson.dto.response.LessonResponse;
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
