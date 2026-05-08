package org.japan.dto.response.common;

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
