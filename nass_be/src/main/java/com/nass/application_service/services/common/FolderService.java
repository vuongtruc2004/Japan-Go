package com.nass.application_service.services.common;

import com.nass.application_service.dto.requests.common.FolderRequest;
import com.nass.application_service.dto.responses.common.FolderResponse;
import com.nass.application_service.exceptions.FolderException;
import com.nass.application_service.exceptions.NotFoundException;
import com.nass.application_service.services.i18n.I18nService;
import com.nass.application_service.services.interfaces.common.IFolderService;
import com.nass.contract.enums.messages.FolderMessageEnum;
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
    public FolderResponse createNewFolder(FolderRequest request) {
        String folderName = request.folderName().trim();
        Long parentId = request.parentId();
        FolderEntity folderEntity = FolderEntity.builder()
                .folderName(folderName)
                .build();
        if (request.parentId() != null) {
            FolderEntity parent = folderRepository.findById(parentId)
                    .orElseThrow(() -> new NotFoundException(
                            i18nService.translation(FolderMessageEnum.FOLDER_NOT_FOUND.key, parentId),
                            i18nService.translation(FolderMessageEnum.FOLDER_NOT_FOUND.key, parentId)
                    ));

            if (folderRepository.existsByFolderNameAndParent(folderName, parent)) {
                throw new FolderException(
                        i18nService.translation(FolderMessageEnum.FOLDER_NAME_EXISTED.key, folderName),
                        i18nService.translation(FolderMessageEnum.FOLDER_NAME_EXISTED.key, folderName)
                );
            }
            folderEntity.setParent(parent);
            parent.getChildren().add(folderEntity);

        } else if (folderRepository.existsByFolderNameAndParentIsNull(folderName)) {
            throw new FolderException(
                    i18nService.translation(FolderMessageEnum.FOLDER_NAME_EXISTED.key, folderName),
                    i18nService.translation(FolderMessageEnum.FOLDER_NAME_EXISTED.key, folderName)
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
