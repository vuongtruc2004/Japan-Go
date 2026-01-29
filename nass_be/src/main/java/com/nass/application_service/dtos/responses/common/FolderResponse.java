package com.nass.application_service.dtos.responses.common;

import com.nass.application_service.dtos.responses.base.BaseResponse;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class FolderResponse extends BaseResponse<Long> {
    String folderName;

    Long parentId;

    List<FolderResponse> children;
}
