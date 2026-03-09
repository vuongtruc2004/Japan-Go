package com.japan_go_be.infrastructure.repositories.common;

import com.japan_go_be.infrastructure.repositories.base.BaseRepository;
import com.japan_go_be.infrastructure.entities.common.FolderEntity;

import java.util.List;

public interface FolderRepository extends BaseRepository<FolderEntity> {
    boolean existsByFolderNameAndParent(String folderName, FolderEntity parent);

    boolean existsByFolderNameAndParentIsNull(String folderName);

    List<FolderEntity> findAllByIsPinnedToSidebarOrderByModifiedTimeDesc(Boolean isPinnedToSidebar);
}
