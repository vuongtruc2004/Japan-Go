package com.japan_go_be.business.services.kanji;

import com.fasterxml.jackson.core.type.TypeReference;
import com.japan_go_be.business.dto.mappers.KanjiDtoMapper;
import com.japan_go_be.business.dto.mappers.kanji.KanjiMapper;
import com.japan_go_be.business.dto.responses.kanji.KanjiResponse;
import com.japan_go_be.business.exceptions.FileNotValidException;
import com.japan_go_be.business.i18n.I18nService;
import com.japan_go_be.business.importers.kanji.KanjiXmlImporter;
import com.japan_go_be.business.services.redis.RedisService;
import com.japan_go_be.business.validators.FileValidator;
import com.japan_go_be.contract.constants.RedisKeys;
import com.japan_go_be.contract.message.FileMessage;
import com.japan_go_be.infrastructure.entities.kanji.KanjiEntity;
import com.japan_go_be.infrastructure.repositories.kanji.KanjiRepository;
import lombok.RequiredArgsConstructor;
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
    private final RedisService redisService;

    /**
     * Get kanji characters by JLPT level
     *
     * @param jlptLevel JLPT level (N1, N2, N3, N4, N5)
     *                  inputted by integer from 1-5
     * @return list kanji response
     * (each kanji response contains only id and kanji character)
     */
    public List<KanjiResponse> getKanjiByJlptLevel(Integer jlptLevel) {
        String redisKey = RedisKeys.getKanjiJlptLevelKey(jlptLevel);
        if (redisService.hasKey(redisKey)) {
            return redisService.get(
                    redisKey,
                    new TypeReference<List<KanjiResponse>>() {
                    }
            );
        }

        List<KanjiEntity> kanjiEntities = kanjiRepository.findAllByJlptLevel(jlptLevel);
        List<KanjiResponse> kanjiResponses = kanjiEntities
                .stream()
                .map(kanjiMapper::mapKanjiEntityToKanjiResponse)
                .toList();
        redisService.save(redisKey, kanjiResponses);
        return kanjiResponses;
    }

    /**
     * Import all kanji characters (13,100 kanji) from KANJIDIC library
     *
     * @param kanjidicFile  KANJIDIC library provides a xml file, I have to parse it to KanjiEntity list then save them to the database
     * @param kanjiJlptFile This json file contains some kanji characters and their JLPT level (N1, N2, N3, N4, N5)
     * @return list kanji response (convert from kanji entity list after saved to DB)
     */
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
        try (InputStream kanjidicInputstream = kanjidicFile.getInputStream();
             InputStream kanjijlptInputstream = kanjiJlptFile.getInputStream()) {
            List<KanjiEntity> kanjiEntities = kanjiXmlImporter.importKanji(kanjidicInputstream, kanjijlptInputstream);
            return kanjiEntities.stream().map(kanjiDTOMapper::kanjiEntityToKanjiResponse).toList();

        } catch (Exception e) {
            throw new FileNotValidException(
                    i18nService.translation(FileMessage.FILE_ERROR, e.getMessage()),
                    i18nService.translation(FileMessage.FILE_ERROR, e.getMessage())
            );
        }
    }
}
