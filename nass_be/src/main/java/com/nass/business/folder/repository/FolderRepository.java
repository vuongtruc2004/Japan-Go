package com.nass.business.folder.repository;

import com.nass.business.folder.entity.FolderEntity;
import com.nass.common.persistence.BaseRepository;

public interface FolderRepository extends BaseRepository<FolderEntity, Long> {
    boolean existsByFolderNameAndParent(String folderName, FolderEntity parent);

    boolean existsByFolderNameAndParentIsNull(String folderName);
}
