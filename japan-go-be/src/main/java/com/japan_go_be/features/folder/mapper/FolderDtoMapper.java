package com.japan_go_be.features.folder.mapper;

import com.japan_go_be.features.folder.dto.response.FolderResponse;
import com.japan_go_be.features.folder.entity.FolderEntity;
import com.japan_go_be.features.lesson.mapper.LessonDtoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FolderDtoMapper {

    private final LessonDtoMapper lessonDtoMapper;

    public FolderResponse folderEntityToFolderResponseSummary(FolderEntity folderEntity) {
        FolderResponse folderResponse = FolderResponse.builder()
                .id(folderEntity.getId())
                .folderName(folderEntity.getFolderName())
                .isPinnedToSidebar(folderEntity.getIsPinnedToSidebar())
                .build();

        if (folderEntity.getParent() != null) {
            folderResponse.setParentId(folderEntity.getParent().getId());
        }

        folderResponse.setLessonCount((long) folderEntity.getLessons().size());
        return folderResponse;
    }

    public FolderResponse folderEntityToFolderResponseDetails(FolderEntity folderEntity) {
        FolderResponse folderResponse = folderEntityToFolderResponseSummary(folderEntity);
        folderResponse.setLessons(folderEntity
                .getLessons()
                .stream()
                .map(lessonDtoMapper::lessonEntityToLessonResponseSummary)
                .toList());
        return folderResponse;
    }
}
