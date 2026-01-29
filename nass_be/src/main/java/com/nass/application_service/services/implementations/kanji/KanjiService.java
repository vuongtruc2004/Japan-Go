package com.nass.application_service.services.implementations.kanji;

import com.nass.application_service.dtos.mappers.KanjiDtoMapper;
import com.nass.application_service.dtos.responses.kanji.KanjiResponse;
import com.nass.application_service.exceptions.FileNotValidException;
import com.nass.application_service.importers.kanji.Kanjidic2XmlImporter;
import com.nass.application_service.services.i18n.I18nService;
import com.nass.application_service.services.interfaces.kanji.IKanjiService;
import com.nass.application_service.validators.FileValidator;
import com.nass.contract.constants.messages.common.FileMessage;
import com.nass.infrastructure.entities.kanji.KanjiEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.List;

@Service
@RequiredArgsConstructor
public class KanjiService implements IKanjiService {
    private final FileValidator fileValidator;
    private final Kanjidic2XmlImporter kanjidic2XmlImporter;
    private final KanjiDtoMapper kanjiDTOMapper;
    private final I18nService i18nService;

    /**
     * Used by function importKanjiFromKanjidic(MultipartFile kanjidicFile, MultipartFile kanjiJlptFile)
     * and auto (command line runner) kanji import service
     *
     * @param kanjidicInputstream  input stream of KANJIDIC xml file
     * @param kanjiJlptInputstream input stream of KANJI_JLPT json file
     * @return list of saved kanji entity
     */
    @Override
    public List<KanjiEntity> importKanjiFromKanjidicIS(InputStream kanjidicInputstream, InputStream kanjiJlptInputstream) {
        return kanjidic2XmlImporter.importKanji(kanjidicInputstream, kanjiJlptInputstream);
    }

    /**
     * Import all kanji characters (13,100 kanji) from KANJIDIC library
     *
     * @param kanjidicFile  KANJIDIC library provides a xml file, I have to parse it to KanjiEntity list then save them to the database
     * @param kanjiJlptFile This json file contains some kanji characters and their JLPT level (N1, N2, N3, N4, N5)
     * @return list kanji response (convert from kanji entity list after saved to DB)
     */
    @Override
    @Transactional
    public List<KanjiResponse> importKanjiFromKanjidicMF(MultipartFile kanjidicFile, MultipartFile kanjiJlptFile) {
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
        try (InputStream kanjidicInputstream = kanjidicFile.getInputStream();
             InputStream kanjijlptInputstream = kanjiJlptFile.getInputStream()) {
            List<KanjiEntity> kanjiEntities = importKanjiFromKanjidicIS(kanjidicInputstream, kanjijlptInputstream);
            return kanjiEntities.stream().map(kanjiDTOMapper::kanjiEntityToKanjiResponse).toList();

        } catch (Exception e) {
            throw new FileNotValidException(
                    i18nService.translation(FileMessage.FILE_ERROR, e.getMessage()),
                    i18nService.translation(FileMessage.FILE_ERROR, e.getMessage())
            );
        }
    }
}
