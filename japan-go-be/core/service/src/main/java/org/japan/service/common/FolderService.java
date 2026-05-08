package org.japan.service.common;

import lombok.RequiredArgsConstructor;
import org.japan.dto.mapper.FolderDtoMapper;
import org.japan.dto.request.common.FolderLessonRequest;
import org.japan.dto.request.common.FolderRequest;
import org.japan.dto.response.common.FolderResponse;
import org.japan.entity.common.FolderEntity;
import org.japan.entity.lesson.LessonEntity;
import org.japan.exception.common.FolderException;
import org.japan.helper.folder.FolderHelper;
import org.japan.helper.lesson.LessonHelper;
import org.japan.i18n.I18nService;
import org.japan.message.common.FolderMessage;
import org.japan.persistence.repository.common.FolderRepository;
import org.japan.persistence.repository.lesson.LessonRepository;
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
