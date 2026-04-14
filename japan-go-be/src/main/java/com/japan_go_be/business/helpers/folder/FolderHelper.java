package com.japan_go_be.business.helpers.folder;

import com.japan_go_be.business.exceptions.NotFoundException;
import com.japan_go_be.business.i18n.I18nService;
import com.japan_go_be.contract.message.common.FolderMessage;
import com.japan_go_be.infrastructure.entities.common.FolderEntity;
import com.japan_go_be.infrastructure.repositories.common.FolderRepository;
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
