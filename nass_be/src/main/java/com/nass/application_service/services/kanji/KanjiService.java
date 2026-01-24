package com.nass.application_service.services.kanji;

import com.nass.application_service.dto.mappers.KanjiDtoMapper;
import com.nass.application_service.dto.responses.kanji.KanjiResponse;
import com.nass.application_service.exceptions.FileNotValidException;
import com.nass.application_service.importers.KanjiDicXmlImporter;
import com.nass.application_service.services.i18n.I18nService;
import com.nass.application_service.services.interfaces.kanji.IKanjiService;
import com.nass.application_service.validators.FileValidator;
import com.nass.contract.enums.messages.FileMessageEnum;
import com.nass.infrastructure.entities.kanji.KanjiEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class KanjiService implements IKanjiService {
    private final FileValidator fileValidator;
    private final KanjiDicXmlImporter kanjiDicXmlImporter;
    private final KanjiDtoMapper kanjiDTOMapper;
    private final I18nService i18nService;

    @Override
    @Transactional
    public List<KanjiResponse> importKanjiFromKanjidic(MultipartFile kanjidicFile, MultipartFile kanjijlptFile) {
        if (!fileValidator.isXMLFile(kanjidicFile)) {
            throw new FileNotValidException(
                    i18nService.translation(FileMessageEnum.FILE_NOT_XML.key),
                    i18nService.translation(FileMessageEnum.FILE_NOT_XML.key)
            );
        }
        if (!fileValidator.isJSONFile(kanjijlptFile)) {
            throw new FileNotValidException(
                    i18nService.translation(FileMessageEnum.FILE_NOT_JSON.key),
                    i18nService.translation(FileMessageEnum.FILE_NOT_JSON.key)
            );
        }
        List<KanjiEntity> list = kanjiDicXmlImporter.importKanji(kanjidicFile, kanjijlptFile);
        return list.stream().map(kanjiDTOMapper::kanjiEntityToKanjiResponse).toList();
    }
}
