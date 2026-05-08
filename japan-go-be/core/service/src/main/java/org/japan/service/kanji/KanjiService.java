package org.japan.service.kanji;

import lombok.RequiredArgsConstructor;
import org.japan.dto.mapper.KanjiDtoMapper;
import org.japan.dto.mapper.kanji.KanjiMapper;
import org.japan.dto.request.kanji.UpdateKanjiMainSinoVietnameseRequest;
import org.japan.dto.response.kanji.KanjiResponse;
import org.japan.entity.kanji.KanjiEntity;
import org.japan.entity.kanji.SinoVietnameseEntity;
import org.japan.exception.FileNotValidException;
import org.japan.exception.NotFoundException;
import org.japan.i18n.I18nService;
import org.japan.importer.kanji.KanjiXmlImporter;
import org.japan.message.FileMessage;
import org.japan.message.kanji.KanjiMessage;
import org.japan.message.kanji.SinoVietnameseMessage;
import org.japan.persistence.repository.kanji.KanjiRepository;
import org.japan.persistence.repository.kanji.SinoVietnameseRepository;
import org.japan.validator.FileValidator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.List;

@Service
@RequiredArgsConstructor
public class KanjiService {
    private final FileValidator fileValidator;
    private final KanjiDtoMapper kanjiDTOMapper;
    private final I18nService i18nService;
    private final KanjiXmlImporter kanjiXmlImporter;
    private final KanjiRepository kanjiRepository;
    private final KanjiMapper kanjiMapper;
    private final SinoVietnameseRepository sinoVietnameseRepository;

    public KanjiResponse updateKanjiMainSinoVietnamese(UpdateKanjiMainSinoVietnameseRequest request) {
        KanjiEntity kanji = kanjiRepository.findById(request.kanjiId())
                .orElseThrow(() -> new NotFoundException(
                        i18nService.translation(KanjiMessage.KANJI_NOT_FOUND, request.kanjiId()),
                        i18nService.translation(KanjiMessage.KANJI_NOT_FOUND, request.kanjiId())
                ));

        SinoVietnameseEntity sinoVietnamese = sinoVietnameseRepository.findById(request.sinoVietnameseId())
                .orElseThrow(() -> new NotFoundException(
                        i18nService.translation(SinoVietnameseMessage.SINO_VIETNAMESE_NOT_FOUND, request.sinoVietnameseId()),
                        i18nService.translation(SinoVietnameseMessage.SINO_VIETNAMESE_NOT_FOUND, request.sinoVietnameseId())
                ));

        kanji.setMainSinoVietnamese(sinoVietnamese);
        KanjiEntity savedKanji = kanjiRepository.save(kanji);
        return kanjiDTOMapper.kanjiEntityToKanjiResponse(savedKanji);
    }

    public KanjiResponse getKanjiByKanjiCharacter(String kanjiCharacter) {
        return kanjiRepository.findByKanjiCharacter(kanjiCharacter)
                .map(kanjiDTOMapper::kanjiEntityToKanjiResponse)
                .orElse(null);
    }

    /**
     * Get kanji characters by JLPT level
     *
     * @param jlptLevel JLPT level (N1, N2, N3, N4, N5)
     *                  inputted by integer from 1-5
     * @return list kanji response
     * (each kanji response contains only id and kanji character)
     */
    public List<KanjiResponse> getKanjiByJlptLevel(Integer jlptLevel) {
        List<KanjiEntity> kanjiEntities = kanjiRepository.findAllByJlptLevel(jlptLevel);
        return kanjiEntities
                .stream()
                .map(kanjiMapper::mapKanjiEntityToKanjiResponse)
                .toList();
    }

    /**
     * Import all kanji characters (13,100 kanji) from KANJIDIC library
     *
     * @param kanjidicFile  KANJIDIC library provides a xml file, I have to parse it to KanjiEntity list then save them to the database
     * @param kanjiJlptFile This json file contains some kanji characters and their JLPT level (N1, N2, N3, N4, N5)
     * @return list kanji response (convert from kanji entity list after saved to DB)
     */
    @Transactional
    public void importKanjiFromKanjidic(MultipartFile kanjidicFile, MultipartFile kanjiJlptFile) {
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
            kanjiXmlImporter.importKanji(kanjidicInputstream, kanjijlptInputstream);
        } catch (Exception e) {
            throw new FileNotValidException(
                    i18nService.translation(FileMessage.FILE_ERROR, e.getMessage()),
                    i18nService.translation(FileMessage.FILE_ERROR, e.getMessage())
            );
        }
    }
}
