package com.nass.business.folder.dto.response;

import com.nass.common.dto.BaseResponse;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class FolderResponse extends BaseResponse<Long> {
    String folderName;

    Long parentId;
}
