package com.japan_go_be.business.services.common;

import com.japan_go_be.business.dto.mappers.FolderDtoMapper;
import com.japan_go_be.business.dto.requests.common.FolderLessonRequest;
import com.japan_go_be.business.dto.requests.common.FolderRequest;
import com.japan_go_be.business.dto.responses.common.FolderResponse;
import com.japan_go_be.business.exceptions.common.FolderException;
import com.japan_go_be.business.helpers.folder.FolderHelper;
import com.japan_go_be.business.helpers.lesson.LessonHelper;
import com.japan_go_be.business.i18n.I18nService;
import com.japan_go_be.contract.message.common.FolderMessage;
import com.japan_go_be.infrastructure.entities.common.FolderEntity;
import com.japan_go_be.infrastructure.entities.lesson.LessonEntity;
import com.japan_go_be.infrastructure.repositories.common.FolderRepository;
import com.japan_go_be.infrastructure.repositories.lesson.LessonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FolderService {

    private final FolderRepository folderRepository;
    private final I18nService i18nService;
    private final FolderHelper folderHelper;
    private final LessonHelper lessonHelper;
    private final LessonRepository lessonRepository;
    private final FolderDtoMapper folderDtoMapper;

    @Transactional
    public void removeLessonFromFolder(FolderLessonRequest request) {
        Long folderId = request.folderId();
        Long lessonId = request.lessonId();
        FolderEntity folder = folderHelper.getFolderById(folderId);
        LessonEntity lesson = lessonHelper.getLessonById(lessonId);

        if (!lessonRepository.existsByLessonIdAndFolderId(lessonId, folderId)) {
            throw new FolderException(
                    i18nService.translation(FolderMessage.FOLDER_CONTAINS_LESSON, folder.getFolderName()),
                    i18nService.translation(FolderMessage.FOLDER_CONTAINS_LESSON, folder.getFolderName())
            );
        }
        folder.getLessons().remove(lesson);
        lesson.getFolders().remove(folder);
    }

    @Transactional
    public void addLessonToFolder(FolderLessonRequest request) {
        Long folderId = request.folderId();
        Long lessonId = request.lessonId();
        FolderEntity folder = folderHelper.getFolderById(folderId);
        LessonEntity lesson = lessonHelper.getLessonById(lessonId);

        if (lessonRepository.existsByLessonIdAndFolderId(lessonId, folderId)) {
            throw new FolderException(
                    i18nService.translation(FolderMessage.FOLDER_CONTAINS_LESSON, folder.getFolderName()),
                    i18nService.translation(FolderMessage.FOLDER_CONTAINS_LESSON, folder.getFolderName())
            );
        }

        folder.getLessons().add(lesson);
    }

    public FolderResponse getFolderById(Long id) {
        FolderEntity folder = folderHelper.getFolderById(id);
        return folderDtoMapper.folderEntityToFolderResponseDetails(folder);
    }

    public Long deleteFolder(Long id) {
        FolderEntity folder = folderHelper.getFolderById(id);

        for (LessonEntity lesson : new ArrayList<>(folder.getLessons())) {
            lesson.getFolders().remove(folder);
        }
        folder.setLessons(new ArrayList<>());
        folderRepository.delete(folder);
        return id;
    }

    public FolderResponse createNewFolder(FolderRequest request) {
        String folderName = request.folderName().trim();
        if (folderName.isEmpty()) {
            throw new FolderException(
                    i18nService.translation(FolderMessage.FOLDER_NAME_BLANK, folderName),
                    i18nService.translation(FolderMessage.FOLDER_NAME_BLANK, folderName)
            );
        }

        FolderEntity folderEntity = FolderEntity.builder()
                .folderName(folderName)
                .build();
        if (request.parentId() != null) {
            FolderEntity parent = folderHelper.getFolderById(request.parentId());

            if (folderRepository.existsByFolderNameAndParent(folderName, parent)) {
                throw new FolderException(
                        i18nService.translation(FolderMessage.FOLDER_NAME_EXISTED, folderName),
                        i18nService.translation(FolderMessage.FOLDER_NAME_EXISTED, folderName)
                );
            }
            folderEntity.setParent(parent);
            parent.getChildren().add(folderEntity);

        } else if (folderRepository.existsByFolderNameAndParentIsNull(folderName)) {
            throw new FolderException(
                    i18nService.translation(FolderMessage.FOLDER_NAME_EXISTED, folderName),
                    i18nService.translation(FolderMessage.FOLDER_NAME_EXISTED, folderName)
            );
        }
        FolderEntity savedFolder = folderRepository.save(folderEntity);
        return folderDtoMapper.folderEntityToFolderResponseSummary(savedFolder);
    }

    public List<FolderResponse> getAllFolders() {
        List<FolderEntity> folders = folderRepository.findAll();
        return folders.stream().map(folderDtoMapper::folderEntityToFolderResponseSummary).toList();
    }

    @Transactional
    public FolderResponse pinAndUnpinFolderFromSidebar(Long folderId) {
        FolderEntity folderEntity = folderHelper.getFolderById(folderId);
        folderEntity.setIsPinnedToSidebar(!folderEntity.getIsPinnedToSidebar());
        return folderDtoMapper.folderEntityToFolderResponseSummary(folderRepository.save(folderEntity));
    }

    public List<FolderResponse> getAllPinFolders() {
        List<FolderEntity> folders = folderRepository.findAllByIsPinnedToSidebarOrderByModifiedTimeDesc(true);
        return folders.stream().map(folderDtoMapper::folderEntityToFolderResponseSummary).toList();
    }
}
