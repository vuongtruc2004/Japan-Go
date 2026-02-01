package com.nass.business.folder.helper;

import com.nass.business.folder.constant.FolderMessage;
import com.nass.business.folder.entity.FolderEntity;
import com.nass.business.folder.repository.FolderRepository;
import com.nass.common.exception.NotFoundException;
import com.nass.common.i18n.I18nService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FolderHelper {
    private final FolderRepository folderRepository;
    private final I18nService i18nService;

    public FolderEntity getFolderById(Long id) {
        return folderRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(
                        i18nService.translation(FolderMessage.FOLDER_NOT_FOUND, id),
                        i18nService.translation(FolderMessage.FOLDER_NOT_FOUND, id)
                ));
    }
}
