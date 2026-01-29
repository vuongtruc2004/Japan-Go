package com.nass.infrastructure.repositories.common;

import com.nass.infrastructure.entities.common.FolderEntity;
import com.nass.infrastructure.repositories.base.BaseRepository;

public interface FolderRepository extends BaseRepository<FolderEntity, Long> {
    boolean existsByFolderNameAndParent(String folderName, FolderEntity parent);

    boolean existsByFolderNameAndParentIsNull(String folderName);
}
