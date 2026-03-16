package com.japan_go_be.business.services.common;

import com.japan_go_be.business.dto.mappers.common.FolderMapper;
import com.japan_go_be.business.dto.responses.common.FolderResponseTest;
import com.japan_go_be.infrastructure.entities.common.FolderEntity;
import com.japan_go_be.infrastructure.repositories.common.FolderRepository;
import lombok.RequiredArgsConstructor;
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
