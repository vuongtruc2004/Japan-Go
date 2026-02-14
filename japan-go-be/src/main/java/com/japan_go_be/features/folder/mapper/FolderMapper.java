package com.japan_go_be.features.folder.mapper;

import com.japan_go_be.features.folder.dto.response.FolderResponse;
import com.japan_go_be.features.folder.entity.FolderEntity;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FolderMapper {
    private final ModelMapper modelMapper;

    public FolderResponse mapFolderEntityToFolderResponse(FolderEntity folderEntity) {
        FolderResponse folderResponse = modelMapper.map(folderEntity, FolderResponse.class);
        folderResponse.setLessonCount((long) folderEntity.getLessons().size());
        return folderResponse;
    }
}
