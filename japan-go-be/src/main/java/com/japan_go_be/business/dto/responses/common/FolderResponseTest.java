package com.japan_go_be.business.dto.responses.common;

import lombok.*;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class FolderResponseTest {
    String folderName;

    Boolean isPinnedToSidebar;

    Long parentId;
}
