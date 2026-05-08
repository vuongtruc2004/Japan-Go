package org.japan.dto.mapper;

import lombok.RequiredArgsConstructor;
import org.japan.dto.mapper.lesson.LessonDtoMapper;
import org.japan.dto.response.common.FolderResponse;
import org.japan.entity.common.FolderEntity;
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
