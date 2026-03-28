package com.japan_go_be.business.dto.mappers;

import com.japan_go_be.business.dto.mappers.lesson.LessonDtoMapper;
import com.japan_go_be.business.dto.responses.common.FolderResponse;
import com.japan_go_be.infrastructure.entities.common.FolderEntity;
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
