package com.nass.application_service.services.kanji;

import com.nass.application_service.dto.mappers.KanjiDtoMapper;
import com.nass.application_service.dto.responses.kanji.KanjiResponse;
import com.nass.application_service.exceptions.FileNotValidException;
import com.nass.application_service.importers.kanji.KanjiDicXmlImporter;
import com.nass.application_service.services.i18n.I18nService;
import com.nass.application_service.services.interfaces.kanji.IKanjiService;
import com.nass.application_service.validators.FileValidator;
import com.nass.contract.constants.messages.common.FileMessage;
import com.nass.infrastructure.entities.kanji.KanjiEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@RequiredArgsConstructor
public class KanjiService implements IKanjiService {
    private final FileValidator fileValidator;
    private final KanjiDicXmlImporter kanjiDicXmlImporter;
    private final KanjiDtoMapper kanjiDTOMapper;
    private final I18nService i18nService;

    /**
     * Import all kanji characters (13,100 kanji) from KANJIDIC library
     *
     * @param kanjidicFile  KANJIDIC library provides a xml file, I have to parse it to KanjiEntity list then save them to the database
     * @param kanjiJlptFile This file contains some kanji characters and their JLPT level (N1, N2, N3, N4, N5)
     * @return list of KanjiResponse
     */
    @Override
    @Transactional
    public List<KanjiResponse> importKanjiFromKanjidic(MultipartFile kanjidicFile, MultipartFile kanjiJlptFile) {
        if (!fileValidator.isXMLFile(kanjidicFile)) {
            throw new FileNotValidException(
                    i18nService.translation(FileMessage.FILE_NOT_XML),
                    i18nService.translation(FileMessage.FILE_NOT_XML)
            );
        }
        if (!fileValidator.isJSONFile(kanjiJlptFile)) {
            throw new FileNotValidException(
                    i18nService.translation(FileMessage.FILE_NOT_JSON),
                    i18nService.translation(FileMessage.FILE_NOT_JSON)
            );
        }
        List<KanjiEntity> list = kanjiDicXmlImporter.importKanji(kanjidicFile, kanjiJlptFile);
        return list.stream().map(kanjiDTOMapper::kanjiEntityToKanjiResponse).toList();
    }
}
