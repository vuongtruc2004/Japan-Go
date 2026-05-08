package org.japan.services.common;

import lombok.RequiredArgsConstructor;
import org.japan.dto.mapper.common.FolderMapper;
import org.japan.dto.response.common.FolderResponseTest;
import org.japan.entity.common.FolderEntity;
import org.japan.repositories.common.FolderRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FolderTest {
    private final FolderRepository folderRepository;
    private final FolderMapper folderMapper;

    public List<FolderResponseTest> getAllFolders() {
        List<FolderEntity> folders = folderRepository.findAll();
        return folders.stream().map(folderMapper::mapEntityToResponse).toList();
    }
}
