package com.japan_go_be.features.folder.repository;

import com.japan_go_be.common.persistence.BaseRepository;
import com.japan_go_be.features.folder.entity.FolderEntity;

import java.util.List;

public interface FolderRepository extends BaseRepository<FolderEntity> {
    boolean existsByFolderNameAndParent(String folderName, FolderEntity parent);

    boolean existsByFolderNameAndParentIsNull(String folderName);

    List<FolderEntity> findAllByIsPinnedToSidebarOrderByModifiedTimeDesc(Boolean isPinnedToSidebar);
}
