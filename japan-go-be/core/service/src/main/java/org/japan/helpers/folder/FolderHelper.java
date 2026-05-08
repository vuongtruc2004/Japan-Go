package org.japan.helpers.folder;

import lombok.RequiredArgsConstructor;
import org.japan.entity.common.FolderEntity;
import org.japan.exceptions.NotFoundException;
import org.japan.i18n.I18nService;
import org.japan.message.common.FolderMessage;
import org.japan.repositories.common.FolderRepository;
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
