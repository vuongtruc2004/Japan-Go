package com.nass.application_service.services;

import com.nass.application_service.dto.mappers.KanjiDtoMapper;
import com.nass.application_service.dto.responses.kanji.KanjiResponse;
import com.nass.application_service.exceptions.FileNotValidException;
import com.nass.application_service.exceptions.NotFoundException;
import com.nass.application_service.importers.KanjiDicXmlImporter;
import com.nass.application_service.services.interfaces.IKanjiService;
import com.nass.application_service.validators.FileValidator;
import com.nass.contract.constants.FileMessage;
import com.nass.infrastructure.entities.kanji.KanjiEntity;
import com.nass.infrastructure.mappers.KanjiMapper;
import com.nass.infrastructure.repositories.KanjiRepository;
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
    private final KanjiRepository kanjiRepository;
    private final KanjiMapper kanjiMapper;

    @Override
    public KanjiResponse getKanjiById(Integer id) {
//        KanjiEntity kanjiEntity = kanjiRepository.findById(id)
//                .orElseThrow(() -> new NotFoundException("Kanji not found!"));
        KanjiEntity kanjiEntity = kanjiMapper.findKanjiById(id);
        if (kanjiEntity == null) {
            throw new NotFoundException("Not found kanji with id " + id);
        }
        return kanjiDTOMapper.kanjiEntityToKanjiResponse(kanjiEntity);
    }

    @Override
    @Transactional
    public List<KanjiResponse> importKanjiFromKanjidic(MultipartFile kanjidicFile, MultipartFile kanjijlptFile) {
        if (!fileValidator.isXMLFile(kanjidicFile)) {
            throw new FileNotValidException(
                    FileMessage.IS_NOT_XML_FILE_DEV,
                    FileMessage.IS_NOT_XML_FILE_USER
            );
        }
        if (!fileValidator.isJSONFile(kanjijlptFile)) {
            throw new FileNotValidException(
                    FileMessage.IS_NOT_XML_FILE_DEV,
                    FileMessage.IS_NOT_XML_FILE_USER
            );
        }
        List<KanjiEntity> list = kanjiDicXmlImporter.importKanji(kanjidicFile, kanjijlptFile);
        return list.stream().map(kanjiDTOMapper::kanjiEntityToKanjiResponse).toList();
    }
}
