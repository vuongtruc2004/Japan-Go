package org.japan.persistence.repository.common;

import org.japan.entity.common.FolderEntity;
import org.japan.persistence.repository.base.BaseRepository;

import java.util.List;

public interface FolderRepository extends BaseRepository<FolderEntity> {
    boolean existsByFolderNameAndParent(String folderName, FolderEntity parent);

    boolean existsByFolderNameAndParentIsNull(String folderName);

    List<FolderEntity> findAllByIsPinnedToSidebarOrderByModifiedTimeDesc(Boolean isPinnedToSidebar);
}
