package com.japan_go_be.features.folder.helper;

import com.japan_go_be.common.exception.NotFoundException;
import com.japan_go_be.common.i18n.I18nService;
import com.japan_go_be.features.folder.constant.FolderMessage;
import com.japan_go_be.features.folder.entity.FolderEntity;
import com.japan_go_be.features.folder.repository.FolderRepository;
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
