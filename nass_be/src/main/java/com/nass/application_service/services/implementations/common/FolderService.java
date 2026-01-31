package com.nass.application_service.services.implementations.common;

import com.nass.application_service.dtos.requests.common.FolderRequest;
import com.nass.application_service.dtos.responses.common.FolderResponse;
import com.nass.application_service.exceptions.FolderException;
import com.nass.application_service.exceptions.NotFoundException;
import com.nass.application_service.services.i18n.I18nService;
import com.nass.application_service.services.interfaces.common.IFolderService;
import com.nass.contract.constants.messages.common.FolderMessage;
import com.nass.infrastructure.entities.common.FolderEntity;
import com.nass.infrastructure.repositories.common.FolderRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FolderService implements IFolderService {

    private final FolderRepository folderRepository;
    private final ModelMapper modelMapper;
    private final I18nService i18nService;

    @Override
    public Long deleteFolder(Long id) {
        FolderEntity folder = folderRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(
                        i18nService.translation(FolderMessage.FOLDER_NOT_FOUND, id),
                        i18nService.translation(FolderMessage.FOLDER_NOT_FOUND, id)
                ));
        folderRepository.delete(folder);
        return id;
    }

    @Override
    public FolderResponse createNewFolder(FolderRequest request) {
        String folderName = request.folderName().trim();
        if (folderName.isEmpty()) {
            throw new FolderException(
                    i18nService.translation(FolderMessage.FOLDER_NAME_BLANK, folderName),
                    i18nService.translation(FolderMessage.FOLDER_NAME_BLANK, folderName)
            );
        }

        Long parentId = request.parentId();
        FolderEntity folderEntity = FolderEntity.builder()
                .folderName(folderName)
                .build();
        if (request.parentId() != null) {
            FolderEntity parent = folderRepository.findById(parentId)
                    .orElseThrow(() -> new NotFoundException(
                            i18nService.translation(FolderMessage.FOLDER_NOT_FOUND, parentId),
                            i18nService.translation(FolderMessage.FOLDER_NOT_FOUND, parentId)
                    ));

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

    @Override
    public List<FolderResponse> getAllFolders() {
        List<FolderEntity> folders = folderRepository.findAll();
        return folders.stream().map(folderEntity -> modelMapper.map(folderEntity, FolderResponse.class)).toList();
    }
}
