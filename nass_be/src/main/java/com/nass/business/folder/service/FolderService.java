package com.nass.business.folder.service;

import com.nass.business.folder.constant.FolderMessage;
import com.nass.business.folder.dto.request.FolderLessonRequest;
import com.nass.business.folder.dto.request.FolderRequest;
import com.nass.business.folder.dto.response.FolderDetailsResponse;
import com.nass.business.folder.dto.response.FolderResponse;
import com.nass.business.folder.entity.FolderEntity;
import com.nass.business.folder.exception.FolderException;
import com.nass.business.folder.helper.FolderHelper;
import com.nass.business.folder.repository.FolderRepository;
import com.nass.business.lesson.entity.LessonEntity;
import com.nass.business.lesson.helper.LessonHelper;
import com.nass.business.lesson.repository.LessonRepository;
import com.nass.common.i18n.I18nService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FolderService {

    private final FolderRepository folderRepository;
    private final ModelMapper modelMapper;
    private final I18nService i18nService;
    private final FolderHelper folderHelper;
    private final LessonHelper lessonHelper;
    private final LessonRepository lessonRepository;

    public FolderResponse addLessonToFolder(FolderLessonRequest request) {
        Long folderId = request.folderId();
        Integer lessonId = request.lessonId();
        FolderEntity folder = folderHelper.getFolderById(folderId);
        LessonEntity lesson = lessonHelper.getLessonById(lessonId);

        if (lessonRepository.existsByLessonIdAndFolderId(lessonId, folderId)) {
            throw new FolderException(
                    i18nService.translation(FolderMessage.FOLDER_CONTAINS_LESSON, folder.getFolderName()),
                    i18nService.translation(FolderMessage.FOLDER_CONTAINS_LESSON, folder.getFolderName())
            );
        }

        folder.getLessons().add(lesson);
        FolderEntity savedFolder = folderRepository.save(folder);
        return modelMapper.map(savedFolder, FolderResponse.class);
    }

    public FolderDetailsResponse getFolderById(Long id) {
        FolderEntity folder = folderHelper.getFolderById(id);
        return modelMapper.map(folder, FolderDetailsResponse.class);
    }

    public Long deleteFolder(Long id) {
        FolderEntity folder = folderHelper.getFolderById(id);
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
        return modelMapper.map(savedFolder, FolderResponse.class);
    }

    public List<FolderResponse> getAllFolders() {
        List<FolderEntity> folders = folderRepository.findAll();
        return folders.stream().map(folderEntity -> modelMapper.map(folderEntity, FolderResponse.class)).toList();
    }
}
